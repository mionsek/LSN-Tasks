The tasks to be solved are visible here:
https://backend-tasks.lsn.io/

All 3 tasks are in the 'master' branch, have output visible in the console and in the 'output/Results[1/2/3].txt' files.
The input files are taken from the 'input' folder, the path is constant and can be changed in the 'Constants' class.


I use a logger in the code and handle exceptions when loading/saving the file.
I am aware that exceptions are not handled when reading integers from a file (e.g. there could be a String instead of a number), but I assume that this is the ideal case where the data is correct :-) .

#Task1
The first task was quite easy, I assumed that there cannot be duplicates in a Set, hence I created a hashSet.
I could also use 'integerList.stream().distinct().toList()', but it was slower in my tests.

#Task2
The second task was not so easy, I had to take into account that when we have input [6 6 6 7 7 7] we do not have 3 pairs, but 9 (a combination of two triples) and I had to take this into account in the code.

#Task3
The third task was definitely the most difficult.
First I tried to do it 'my way' - using:
    Set<Pair> connectionsSet and HashSet<TreeSet<Integer>> createdConnections
I managed to solve the task (you can see it in the commit history), but it was terrifyingly slow for larger data. So I decided to google and managed to find help on stackoverflow and wikipedia:
https://en.wikipedia.org/wiki/Disjoint-set_data_structure
It took me a long time to understand this, but I hope my reasoning is correct.

However, I still ignore the initial number of connections (pairs), and I think it may speed up the program.
