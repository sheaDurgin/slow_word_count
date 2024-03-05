SLOW Word Count

Objective:
Develop a program that counts the occurrences of each word (case insensitive) in an external text file, focusing on the use of the least efficient algorithms and structures possible.

Challenge Details:
Input: An external text file with a word count of 1 to 5,000 words.
Output: A list of unique words in the text and the number of times each word appears, in any order. 
<br>
Example:<br>

Input “VividCloud is a great place vividcloud is cool”<br>

Output: <br>
vividcloud 2<br>
is 2<br>
a 1<br>
great 1<br>
place 1<br>
cool 1<br>


Constraints:
No Artificial Delays: Solutions must not include sleep timers, deliberate infinite loops, or any hardcoded delays.
Standard Libraries Only: Use of external libraries is prohibited. Solutions should rely solely on standard libraries included in the language's default installation.
Algorithmic and Structural Inefficiency: Inefficiencies should arise from the choice of algorithms, data structures, and processing techniques. This includes, but is not limited to, unnecessary data structures, repeated scans of the text, inefficient string handling, and the avoidance of efficient lookup methods.
Cannot keep a timer and then return once you're close to the 5 minutes.
No measuring.
A single solution for all tests.

Correctness Mandatory: Despite the inefficiency, solutions must accurately count word occurrences.

Execution Time Limit: Solutions must execute within 5 minutes.

File Input Requirement: Participants must implement a method to read the text file from an external source. The file path can be provided via the command line or hardcoded into the program by the organizer before execution.

Inefficiency Boundary Rules:
Repeated File Reading: Solutions may read the file multiple times unnecessarily.
Inefficient Data Management: Using inefficient data structures for word storage and counting, such as lists or arrays for linear searches, is encouraged.
Manual Parsing: Solutions can include manual parsing of the text for word separation and counting, avoiding efficient string handling techniques.
Unnecessary Complexity: Implementing redundant steps or calculations that don't contribute to the final result adds to the desired inefficiency.

Evaluation Criteria:
Accuracy: Correct and accurate counting of word occurrences is paramount.
Inefficiency: Solutions will be ranked on their inefficiency levels and the creativity of their approach.
Originality: Unique approaches to introducing inefficiencies are encouraged and will be a factor in evaluation.
Compliance with Constraints: Adherence to all challenge constraints, including the input method requirement and execution time limit.

Submission Guidelines:
All submissions must be provided via email or merge request in GitHub by midnight on Sunday night.
