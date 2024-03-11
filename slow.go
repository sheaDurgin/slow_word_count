package main

import (
	"bufio"
	"encoding/json"
	"fmt"
	"log"
	"math/rand"
	"os"
	"regexp"
	"sort"
	"strings"
	"time"
)

func main() {
	start := time.Now()

	input := readStdin()
	if input == "" {
		fmt.Println("Input needs to be piped via stdin... echo \"blah blah blah\" | go run slow.go")
		os.Exit(1)
	} else {
		process(clean(input))
	}

	fmt.Printf("Execution took %v seconds\n", time.Since(start).Seconds())
}

func process(words string) {
	wordCountSampleMap := make(map[string]float64)
	wordCountPopulationEstimateMap := make(map[string]int)
	wordArray := strings.Split(words, " ")

	iterations := 6000000000

	for i := 0; i < iterations; i++ {
		index := randRange(0, len(wordArray))
		wordCountSampleMap[strings.ToLower(wordArray[index])]++
	}

	for word, count := range wordCountSampleMap {
		wordCountPopulationEstimateMap[word] = int(count/float64(iterations)*float64(len(wordArray)-1)) + 1
	}

	output(wordCountPopulationEstimateMap)
}

func readStdin() string {
	stat, _ := os.Stdin.Stat()
	if (stat.Mode() & os.ModeCharDevice) == 0 {
		var stdin string
		scanner := bufio.NewScanner(os.Stdin)
		for scanner.Scan() {
			stdin = scanner.Text()
		}
		if err := scanner.Err(); err != nil {
			log.Fatal(err)
		}

		return stdin
	}

	return ""
}

func output(data map[string]int) {
	sortMap(data)
	dataAsJson, _ := json.MarshalIndent(data, "", "  ")
	fmt.Println(string(dataAsJson))
}

func clean(str string) string {
	return regexp.MustCompile(`[^a-zA-Z0-9 ]+`).ReplaceAllString(str, "")
}

func randRange(min, max int) int {
	return rand.Intn(max-min) + min
}

func sortMap(data map[string]int) {
	keys := make([]string, 0, len(data))
	for key := range data {
		keys = append(keys, key)
	}
	sort.Slice(keys, func(i, j int) bool { return data[keys[i]] > data[keys[j]] })
}
