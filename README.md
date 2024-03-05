# slow_word_count

**Objective**:<br>
Develop a program that counts the occurrences of each word (case insensitive) in an external text file, focusing on the use of the least efficient algorithms and structures possible. 

<br>
**Challenge Details**:
<br>Input: An external text file with a word count ranging from 1 to 5,000 words. 
<br>Output: A list of unique words in the text and the number of times each word appears, in any order.
<be>
<be>
**Constraints**:<br>
No Artificial Delays: Solutions must not include sleep timers, deliberate infinite loops, or any hardcoded delays.
<br>Standard Libraries Only: Use of external libraries is prohibited. Solutions should rely solely on standard libraries included in the language's default installation.
<br>Algorithmic and Structural Inefficiency: Inefficiencies should arise from the choice of algorithms, data structures, and processing techniques. This includes, but is not limited to, unnecessary data <br>structures, repeated scans of the text, inefficient string handling, and the avoidance of efficient lookup methods.
<br>Correctness Mandatory: Despite the inefficiency, solutions must accurately count word occurrences.
<br>Execution Time Limit: Solutions must execute within 5 minutes in a standard testing environment.
<br>File Input Requirement: Participants must implement a method to read the text file from an external source. The file path can be provided via the command line or hardcoded into the program by the organizer <br>before execution.<br>
<br>
<br>**Inefficiency Boundary Rules**:
<br>Repeated File Reading: Solutions may read the file multiple times unnecessarily.
<br>Inefficient Data Management: Using inefficient data structures for word storage and counting, such as lists or arrays for linear searches, is encouraged.
<br>Manual Parsing: Solutions can include manual parsing of the text for word separation and counting, avoiding efficient string handling techniques.
<br>Unnecessary Complexity: Implementing redundant steps or calculations that don't contribute to the final result adds to the desired inefficiency.
<br>
<br>**Evaluation Criteria**:
<br>Accuracy: Correct and accurate counting of word occurrences is paramount.
<br>Inefficiency: Solutions will be ranked on their inefficiency levels and the creativity of their approach.
<br>Originality: Unique approaches to introducing inefficiencies are encouraged and will be a factor in evaluation.
<br>Compliance with Constraints: Adherence to all challenge constraints, including the input method requirement and execution time limit.
<br>Submission Guidelines:
<br>All submissions must be provided via email or merge request in GitHub by midnight on Sunday night.
