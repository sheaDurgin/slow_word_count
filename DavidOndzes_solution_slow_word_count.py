import sys

# Disable bytecode generation
sys.dont_write_bytecode = True

unique_words = set()
word_count = dict()
prime_sizes = set()
fib_sizes = set()
palindrome_words = set()

def is_palindrome(s):
    # Remove non-alphanumeric characters and convert to lowercase
    s = ''.join(char.lower() for char in s if char.isalnum())
    return s == s[::-1]

def is_perfect_square(n):
    return int(n**0.5)**2 == n

def is_fibonacci(n):
    if n <= 0:
        return False
    return is_perfect_square(5 * n**2 + 4) or is_perfect_square(5 * n**2 - 4)

def is_prime(n):
    if n <= 1:
        return False
    if n == 2:
        return True
    if n % 2 == 0:
        return False
    for i in range(3, int(n**0.5) + 1, 2):
        if n % i == 0:
            return False
    return True

def store_string(input_string):
#    print(input_string)
    # Convert the string to a list of characters
    char_list = list(input_string)
    
    # Reverse the order of the characters
    char_list.reverse()

    ascii_sum = 0
    for char in char_list:
        ascii_sum += sum(ord(c) for c in char)

    if is_prime(ascii_sum):
        global prime_sizes

        #prime_sizes.add(ascii_sum)
        prime_sizes = add_to_set(ascii_sum, prime_sizes)

    if is_fibonacci(ascii_sum):
        global fib_sizes
        
        #fib_sizes.add(ascii_sum)
        fib_sizes = add_to_set(ascii_sum, fib_sizes)

    if is_palindrome(input_string):
        global palindrome_words
        
        #palindrome_words.add(input_string)
        palindrome_words = add_to_set(input_string, palindrome_words)
        
    # store words in a dictionary
    # key - ascii sum of characters
    # value - dictionary
    #         key - tuple of charactor list
    #         value - count of that character list
    if ascii_sum in word_count:
        words = word_count[ascii_sum]
        if tuple(char_list) in words:
            words[tuple(char_list)] += 1
        else:
            words[tuple(char_list)] = 1
    else:
        word_count[ascii_sum] = {tuple(char_list) : 1}

def print_unique_counts():

    for word in unique_words:
        # Convert the string to a list of characters
        char_list = list(word)

        # Reverse the order of the characters
        char_list.reverse()

        ascii_sum = 0
        for char in char_list:
            ascii_sum += sum(ord(c) for c in char)

        words = word_count[ascii_sum]
        print(word," : ", words[tuple(char_list)])

def add_to_set(item, addTo):
    newset = set()
    newset.add(item)
    oldset = set(addTo)
    addTo.clear()
    superset = oldset | newset
    return superset
    
def count_unique_words(filename):
    global unique_words
    
    # First pass: Read file and add each word to a set
#    with open(filename, 'r') as file:
#        for line in file:
#            words = line.split()
#            for word in words:
#                unique_words.add(word.strip().lower())

    bytesRead = 0
    wordCount = 0
    line = ""        
    while True:
        with open(filename, 'rb') as file:
            file.seek(bytesRead)
            bchar = file.read(1)  # Read one character at a time
            char = bchar.decode('utf-8')
            bytesRead += 1
            if not char:  # End of file
                break
            if char != '\n':  # Append non-newline characters to the result string
                line += char
            else:  # Reset the result string when encountering a newline
                words = line.split()
                for word in words:
                    #newset = set()
                    #newset.add(word.strip().lower()))
                    #oldset = set(unique_words)
                    #unique_words.clear()
                    #unique_words = oldset | newset
                    tempset = set(unique_words)
                    unique_words = add_to_set(word.strip().lower(), tempset)
                line = ""


    print("Unique Word Count: ", len(unique_words))
    
    # Second pass: Count the occurrences of each unique word
    bytesRead = 0
    wordCount = 0
    line = ""        
    while True:
        if file.closed:
            with open(filename, 'rb') as file:
                file.seek(bytesRead)
                bchar = file.read(1)  # Read one character at a time
                char = bchar.decode('utf-8')
                bytesRead += 1
                if not char:  # End of file
                    break
                if char != '\n':  # Append non-newline characters to the result string
                    line += char
                else:  
                    words = line.split()
                    for word in words:
                        store_string(word.strip().lower())
                    line = ""
                                        
    # Second pass: Count the occurrences of each unique word
#    with open(filename, 'r') as file:
#        for line in file:
#            words = line.split()
#            for word in words:
#              store_string(word.strip().lower())
              

filename = 'sample.txt'  # Change this to your file path
filename = 'words5000.txt'  # Change this to your file path
count_unique_words(filename)
print_unique_counts()
    
