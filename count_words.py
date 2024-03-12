import sys
import random
import string
import time

# Shea Durgin

# Format time into minutes:seconds
def get_time(start, end):
    seconds = end - start
    minutes = seconds // 60
    seconds %= 60
    return f'{str(int(minutes)).zfill(2)}:{str(int(seconds)).zfill(2)}'

# tqdm clone, but rainbow
def print_progress_bar(start, current, total, bar_length=50):
    progress = current / total
    end = time.time()

    colored_bar = ''
    for i in range(bar_length):
        if i < progress * bar_length:
            color_code = random.choice(['\033[91m', '\033[93m', '\033[92m', '\033[96m', '\033[94m', '\033[95m'])
            colored_bar += color_code + '=' + '\033[0m'
        else:
            colored_bar += ' '

    print(f'\r\33[2KProgress: [{colored_bar}] [{current}/{total}] \t[{get_time(start, end)}]', end='', flush=True)

# seed for reproducibility
random.seed(42)

start = time.time()

# this reads in a text filename from the command line
# example usage: python count_words.py example.txt
with open(sys.argv[1], 'r') as f:
    text = f.read().lower()
    words = ''.join([c for c in text if c not in string.punctuation])
    words = words.split()

# copy the original list of words
original_words = words.copy()

# add a copy of words to itself until it reaches 2500 words
# then we can divide the final counts by how many times we added to the words list
divisor = 1
while len(words) < 2500:
    divisor += 1
    words += original_words

# set aside any words past 2500
# these will be counted quickly at the end
words_set_aside = []
if len(words) > 2500:
    words_set_aside = words[2500:].copy()
    words = words[:2500].copy()

# counts dic has word to count mapping
counts = {}
# holds the indices of the words we've visited
visited = set()
# max_attempts is the number of times we've tried to add a word to visited
# threshold is the number of times we're allowed to fail adding a word to visited before we reset visited and counts
# increment is the amount we increase the threshold by each time we reset
max_attempts, threshold, increment = 0, 0, 50 # 22 on mac, 50 on windows

# loop until we've visited every index
while len(visited) < len(words):
    # pick a random index
    i = random.randint(0, len(words) - 1)
    # increment count if not visited, else increment max_attempts
    if i not in visited:
        visited.add(i)
        counts[words[i]] = counts.get(words[i], 0) + 1
    else:
        max_attempts += 1

    # reset visited and counts if we've tried to add a word to visited more than threshold times
    if max_attempts > threshold:
        threshold += increment
        visited = set()
        counts = {}
        max_attempts = 0

    # print rainbow progress bar, yay!
    print_progress_bar(start, len(visited), len(words))

# add counts from words_set_aside to counts
for word in words_set_aside:
    counts[word] = counts.get(word, 0) + 1

# divide counts by divisor
for word, count in counts.items():
    counts[word] = count / divisor

# sort by count, then by string for ties
sorted_lst_of_counts = sorted(counts.items(), key=lambda x: (x[1], x[0]), reverse=True)
end = time.time()

print(sorted_lst_of_counts)
print(f"Final Time: {get_time(start, end)}")
