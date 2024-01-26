# Assignment 1

## Overview

I tried to optimize the isPrime function to reduce the runtime to O(sqrt(n)/6). It has two base cases: if n is less than or equal to one, then it is not a prime number; and if n is equal to 2 or 3, then it is a prime number. Additionally, we check if n is divisible by 2 or 3; if it is, then it is not a prime number. The code implements a 6k+-1 optimization, checking if n is divisible by any of these potential divisors of that form.

## Experimental Evaluation :


The program was experimented with different numbers of threads, demonstrating improved efficiency as the thread count increased:

- **2 Threads**
  - *Runtime*: 25 Seconds
  - *Primes Count*: 5761455
  - *Primes Sum*: 279209790387276
  - *Top Ten Maximum Primes*: [99999787, 99999821, 99999827, 99999839, 99999847, 99999931, 99999941, 99999959, 99999971, 99999989]

- **4 Threads**
  - *Runtime*: 15 Seconds
  - *Primes Count*: 5761455
  - *Primes Sum*: 279209790387276
  - *Top Ten Maximum Primes*: [99999787, 99999821, 99999827, 99999839, 99999847, 99999931, 99999941, 99999959, 99999971, 99999989]

- **6 Threads**
  - *Runtime*: 12 Seconds
  - *Primes Count*: 5761455
  - *Primes Sum*: 279209790387276
  - *Top Ten Maximum Primes*: [99999787, 99999821, 99999827, 99999839, 99999847, 99999931, 99999941, 99999959, 99999971, 99999989]

- **8 Threads**
  - *Runtime*: 11 Seconds
  - *Primes Count*: 5761455
  - *Primes Sum*: 279209790387276
  - *Top Ten Maximum Primes*: [99999787, 99999821, 99999827, 99999839, 99999847, 99999931, 99999941, 99999959, 99999971, 99999989]

## Conclusion

-	As we notice, the higher the number of threads, the lower the runtime to find all prime numbers.
