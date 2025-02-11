class Solution {
    private long countSteps(long curr, long n) {
        long steps = 0;
        long first = curr, last = curr; // First and last denote the range of numbers under the prefix.
        
        // Expand the range [first, last] within the bounds of n
        while (first <= n) {
            // The number of valid numbers in this range
            steps += Math.min(n + 1, last + 1) - first;
            first *= 10; // Move to the next level (deeper in the prefix tree)
            last = last * 10 + 9; // Expand to cover all numbers under the prefix
        }
        return steps;
    }
    public int findKthNumber(int n, int k) {
        long curr = 1;  // Start from prefix '1'
        k--;  // Decrement k because we are counting from 1 (1-based index)
        
        while (k > 0) {
            long steps = countSteps(curr, n); // Count steps under the current prefix
            
            if (steps <= k) { // If k is greater than or equal to the steps, move to next prefix
                curr++; // Move to next number in lexicographical order
                k -= steps; // Reduce k by the number of steps skipped
            } else {
                curr *= 10;  // Move deeper into the tree (next lexicographical level)
                k--; // Since we move one step down, decrease k
            }
        }
        
        return (int) curr; // Return the found number
    }
}
