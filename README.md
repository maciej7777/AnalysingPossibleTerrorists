# PmdAnalysingPossibleTerrorists
Student project based on combinatorial aspects of mathematics. The task is a simulation of a following environment:

1. There are **n** people whose actions are being observed.
2. Each person goes to the hotel with probability **p** each day (so for example p 0.01 means that every person goes to the hotel average once per 100 days). 
3. There are **h** hotels.
4. The simulation takes **d** days.

The main goal of the project is to simulate the environment and find the number of "suspected" people (separately and as a pairs). People are suspected of terrorism when the meet in the hotel at least twice. The probability of such a situation is really small, but for a big input data it will take place (and it is statistically normal, so it cannot be understand as a special situation).

Code of the project has to return on the outputs following data:
- number of suspected people
- number of suspected pairs of people
- the histogram of number of suspected pairs according to number of meetings

# Exemplary inputs and outputs
Please note, that output will vary each time you run a simulation. The proper output need to be just close to the output given below.

Input 1 (n p h d):
- 100000 0.01 100 100

Output 1:
- Suspected pairs: 24
- Suspected people: 48
- Histogram: {0=0, 1=501324, 2=24}


Input 2 (n p h d):
- 1000 0.3 100 100

Output 2:
- Suspected pairs: 1873
- Suspected people: 959
- Histogram: {0=0, 1=41151, 2=1820, 3=52, 4=1}
