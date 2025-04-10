# ZyloBench

ZyloBench is a simple and fast benchmarking tool designed to help you measure the performance of your awesome libraries or frameworks. It aims to be lightweight, flexible, and blazing fast.

> **⚠ Experimental, but works great!** ZyloBench is still evolving, but it's stable enough to use for real benchmarking scenarios.

## Installation

**Gradle**

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}
```

```groovy
dependencies {
    implementation 'com.github.vansencool:ZyloBench:1.0.2'
}
```

**Maven**

```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```

```xml
<dependency>
    <groupId>com.github.vansencool</groupId>
    <artifactId>ZyloBench</artifactId>
    <version>1.0.2</version>
</dependency>
```

---

## 🚀 Getting Started

### Basic Usage
```java
ZyloBench.make()
        .add(Normal.as("If", () -> {
            if (true) {
            }
        }))
        .go();
```
That’s it — your first benchmark is ready!

---

## 🧠 Advanced Benchmarking
ZyloBench also offers advanced benchmarking with customizable data flow:

```java
ZyloBench.make()
        .add(ZyloBench.advanced("")
                .as("String print")
                .value(() -> "", value -> value + " ^ ")
                .execute(value -> {
                    System.out.println(value);
                })
                .build())
        .go();
```

This will print:
```
 ^
 ^  ^
 ^  ^  ^
 ^  ^  ^  ^
...
```
Each line adds one more layer based on the transformation logic you provide.

- The first argument in `value(...)` is the initializer.
- The second is the updater (transforms the previous value).
- The `.execute(...)` runs logic using that value.

The parameter to `advanced(...)` determines the data type — you can pass a mock value like `""` for `String`, or any class instance.

---

## ⚙ Configuration Options

All configuration methods are in the ZyloBench class.

### `.accuracy(int digits)`
Sets decimal precision.
- Example: `2 = 0.00`, `3 = 0.000`
- **Default:** 2

### `.warm(int count)`
Sets the number of warmup iterations before actual benchmarking.
- **Example:** `.warm(100)`

### `.runs(int count)`
Sets the number of actual benchmarking runs.
- **Example:** `.runs(300)`

### `.calculateBasedOn(CalculateType type)`
Chooses how to calculate the final benchmark result.

Available `CalculateType` options:
- `MIN` — Minimum time
- `AVG` — Average time
- `MAX` — Maximum time
- `P75` — 75th percentile
- `P99` — 99th percentile
- `TOP1` — Top 1% fastest times

### `.timeUnit(TimeUnit unit)`
Changes the **displayed** unit of time.
- **Benchmarking always happens in nanoseconds**, this only affects the printed results.
- Example: `.timeUnit(TimeUnit.MILLISECONDS)`

---
