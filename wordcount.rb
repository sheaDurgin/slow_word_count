# require "Benchmark"

FILE_PATH = "testFile2.txt"

class CountWords
    #
    # Alternative implementation that creates an array to
    # hold the words we found. For each unique word, it 
    # scans the file for other occurrences of that word
    # and prints out the count.
    # It repeats until no more words.
    #
    def countUsingMemory(fPath) 

        offset = 0
        uniqueWords = Array.new
        while true do
            word, offset = self.nextWord(fPath, offset)

            if word == "" then
                # No more words. We're done.
                return
            end

            # puts word + "[" + offset.to_s + "]"

            # See if we've seen this word before
            foundBefore = false
            for w in uniqueWords do
                if !foundBefore then
                    foundBefore = w.downcase.eql?(word.downcase)
                end
            end
            if  !foundBefore then
                uniqueWords.push(word)
                
                # Go back through the file and count all occurrences of this word
                count = findAllTheWords(fPath, word)

                # Write out the total
                puts(word + ": " + count.to_s)
            end
        end
    end

    #
    # This implementation finds a word in the input file,
    # creates a temp file and dumps the word into the temp file.
    # It does this for every word in the input file.
    # It then will loop through all the created tmp files
    # and count each line to determine the number of occurrences 
    # of each word.
    #
    def countWithFiles(fPath)
        offset = 0
        while true do
            word, offset = self.nextWord(fPath, offset)

            if word == "" then
                break
            end
            if !Dir.exist?("tmp") then
                # puts "Creating tmp"
                Dir.mkdir("tmp")
            end

            File.open("tmp/#{word}.cnt", "a") { |fp|
                # puts "Writing #{word}"
                fp.write(word + "\r")
            }
            
        end
        
        Dir.foreach("tmp") { |f| 
            # puts "Checking #{f}"
            if f.end_with?(".cnt")
                n = countLines(f)
                puts "#{f.sub(/\.cnt$/,"")}: #{n}"

                File.delete("tmp/#{f}")
            end
        }
        Dir.delete("tmp")
            
    end

    def countLines(fName)
        cnt = 0
        File.open("tmp/#{fName}") { |fp| 
            while ch = fp.read(1) 
                if ch == "\r" || ch == "\n" then
                    cnt += 1
                end
            end
        } 
        return cnt
    end

    def nextWord(fPath, offset) 
        File.open(fPath, "r") { |fp|
        
            fp.seek(offset)
            word = ""

            # Find the start of a word
            while ch = fp.read(1)
                offset += 1

                if ch != " " && ch != "\r" && ch != "\n" then
                    break
                end
            end
            if ch != nil

                # Keep reading characters until word break
                word += ch
                while ch = fp.read(1)
                    offset += 1

                    if ch != " " && ch != "\r" && ch != "\n" then
                        word += ch
                    else
                        break
                    end
                end
            end
            return word, offset
        }
    end

    def findAllTheWords(fPath, word) 
        count = 0
        offset = 0

        loop do
            # puts "Looking for [#{word.downcase}] Starting at #{offset.to_s}"
            nextWord, offset = self.nextWord(fPath, offset)
            if nextWord == ""
                break
            end
            # puts "--- found [#{nextWord.downcase}]"
            count += 1 if nextWord.downcase.eql?(word.downcase)
        end

        return count
    end
end

wordCounter = CountWords.new
wordCounter.countUsingMemory(FILE_PATH)

# Count with files is actually faster! So, just leaving it commented out.
#wordCounter.countWithFiles(FILE_PATH)

