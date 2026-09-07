# A2 Worksheet: Designing the Mothership

**Name: Abhikruthi Sudula**
**Onyen: Sudula**

Three questions, 15 points, about fifteen minutes. Do this before you write any
code; everything you need is in `README.md` and restated below. You should not
need to open a single `.java` file to answer these. Write your answers directly
under each prompt.

---

## Question 1: Vocabulary of the hierarchy (5 points)

**1a.** For each pair, write **IS-A** or **HAS-A**, plus a half-sentence saying
how you know. (Remember: IS-A means one class extends the other; HAS-A means one
class stores the other in a field.)

| Pair | IS-A or HAS-A? | How you know                                                            |
|---|----------------|-------------------------------------------------------------------------|
| `FuelGenerator` → `APowerGenerator` | IS-A           | FuelGenerator extends from APowerGenerator                              |
| `Mothership` → `ThrusterModule` | HAS-A          | Mothership uses ThrusterModule as an argument                           |
| `SolarGenerator` → `AModule` | IS-A           | SolarGenerator is a APowerGeneratior which is a AModule                  |
| `ExperimentModule` → `double[] parameters` | HAS-A          | ExperimentModule has double[] parameters as a field for its constructor |

**1b.** Both `AModule` and `APowerGenerator` are declared `abstract`, but only
`APowerGenerator` declares an **abstract method** (`generatePower()`). These are
two different design decisions doing two different jobs.

- Marking the *class* abstract stops a programmer from doing what?

```
Marking a class abstract stops a programmer from creating an object
of that class directly.
```

- Marking the *method* abstract forces a programmer to do what?

```
It forces subclasses to implement/override that abstract method.
```

**1c.** `ThrusterModule`, `SolarGenerator`, and `ExperimentModule` each override
`statusReport(...)`, print one line of their own, and then call
`super.statusReport(...)`. Suppose a classmate writes their override, prints their
own line, and forgets the `super` call. Will the compiler complain? What is
actually lost, and how would the student find out?

```
No. The compiler won't complain. The parent class's statusReport 
behavior is lost, and the student would notice by testing the program 
and seeing missing output.
```

---

## Question 2: One mission, by hand (6 points)

You do not need the code for this. Here are the only rules that matter, copied
from the spec:

- `FuelGenerator.generatePower()`: if `fuel >= 10`, return `10` and subtract 10
  from `fuel`. Otherwise, return whatever `fuel` is left and set `fuel` to 0.
- `SolarGenerator.generatePower()`: always returns `10`. It tracks no state.
- `ThrusterModule.thrust(int availablePower)`: a thrust succeeds only if the
  thruster has **at least 5 fuel** *and* `availablePower` is **at least 5**. On
  success, subtract 5 from the thruster's fuel, set `lastFired` to `true`, and
  return `true`. Otherwise set `lastFired` to `false` and return `false`. A
  failed thrust burns no fuel.
- `ThrusterModule` always starts with **100 fuel** and `lastFired = false`.

**2a.** `Main` builds a `FuelGenerator` with **22 fuel** and hands it to the
mothership. The mission then runs four rounds; each round is one
`requestPower()` immediately followed by one `fireThruster(power)` using the
value that was just returned. Fill in the table.

| Round | Power returned | Generator fuel after | Thruster fuel after | Thrust succeeded? | `lastFired` |
|---|----------------|----------------------|---------------------|-------------------|-------------|
| start | —              | 22                   | 100                 | —                 | false       |
| 1 | 10             | 12                   | 95                  | yes               | true        |
| 2 | 10             | 2                    | 90                  | yes               | true        |
| 3 | 2              | 0                    | 90                  | no                | false       |
| 4 | 0              | 0                    | 90                  | no                | false       |

**2b.** Now change **one line in `Main`** so the ship launches with a
`SolarGenerator` instead. Nothing inside `Mothership` changes. Redo rounds 3 and
4 only.

| Round | Power returned | Thruster fuel after | Thrust succeeded? |
|---|----------------|---------------------|-------------------|
| 3 | 10             | 85                  | true              |
| 4 | 10             | 80                  | true              |

Which line in `Main` changed, and what is it about the **declared type** of the
mothership's generator field that made that one line enough?

```
The FuelGenerator now uses SolarGenerator and always generates and returns 10
regardless of the state. The Mothership can call generatePower() without 
needing to know which specific type of generator it has.
```

**2c.** On the solar ship, the thruster will eventually stop firing anyway.
Which round is the first failed thrust, and why? Show the arithmetic.

```
The first failed thrust is Round 21 because the thruster starts with 
100 fuel and uses 5 fuel per successful thrust. 100/5 = 20 So Rounds
1–20 succeed, and Round 21 fails because the thruster has 0 fuel.
```

---

## Question 3: Design pressure (4 points)

**3a.** Part 4 has you add an overloaded `Mothership` constructor whose fourth
parameter is typed `AModule`, not the name of the specific module class you
invented. A classmate says "that's silly, I know mine is a `ShieldModule`, I
should just say `ShieldModule`." Give them one concrete thing that breaks —
something the ship could do with the `AModule` version that it could not do with
theirs.

```
If the constructor used ShieldModule instead of AModule, it would break when we 
tried to pass another module, like WeaponModule. Using AModule allows any subclass 
to be passed in and still use the parent class methods.
```

**3b.** You ask an AI assistant to help wire up the mothership and it proposes
this design, in words:

> "Give `Mothership` two fields, `private SolarGenerator solar;` and
> `private FuelGenerator fuel;`, plus a `private boolean usingSolar;`. Then
> `requestPower()` checks the flag with an `if` and calls `generatePower()` on
> whichever one is active. This is clearer than the abstract class because you
> can see exactly which generator you're using."

The code would compile and the tests for a two-generator ship would pass. Say
what is wrong with it anyway. Name specifically what the team has to do when a
third generator (say, `ReactorGenerator`) is added later, and contrast that with
what the spec's design requires.

```
When a third generator like ReactorGenerator is added, the team would have to add
another field, update the usingSolar logic and if statements, and change requestPower(). 
With the abstract-class design, they only need to create ReactorGenerator as a subclass 
of AGenerator; Mothership does not need to change.
```

---

## Submitting

Turn this in with your answers, as a .md on Gradescope. The code goes to
Gradescope separately; see `README.md`.
