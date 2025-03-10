package leetcode;

// https://leetcode.com/problems/check-if-number-is-a-sum-of-powers-of-three/description/
//
// Constraints and edge cases
// - What's the max num that can be passed in?
// - Can powers repeat? (no, it says distinct)

class PowerofThree {
    public static void main(String[] args) {
        PowerofThree example = new PowerofThree();

        System.out.println(example.checkPowersOfThree(21));
        System.out.println(example.checkPowersOfThree(1));
        System.out.println(example.solution(6574365));
    }

    // Sudocode:
    // Find the base power that it can be
    // - Essentially, log the equation and floor the answer (this will give us the best answer).
    // Calculate power
    // -> Subtract from num and recurse (will also need to keep track of what power we're at).
    // It will go down to a base case of where n is 0 or (n is 1 and it can be power'ed)
    // -> return true
    // -> return false if can't power and n > 1
    // This is incorrect
    public boolean checkPowersOfThree(int n) {
        int power = logBase3(n);

        for (int i = power; 0 <= i; i--) {
            boolean check = subCheck(n - (int)Math.pow(3, i), i);

            if (check) {
                return true;
            }
        }

        return false;
    }

    // This is pretty similar to the first fn except it keeps track on the current power we're in.
    private boolean subCheck(int n, int power) {
        // base case
        if (n == 0 || (n == 1 && power > 0)) {
            return true;
        } else if (n > 1 && power > 0) {
            // recurse
            int newPower = logBase3(n);

            if (newPower >= power) {
                newPower = Math.max(0, power - 1);
            }

            for (int i = newPower; 0 <= i; i--) {
                boolean check = subCheck(n - (int)Math.pow(3, i), i);

                if (check) {
                    return true;
                }
            }

            return false;
        }

        return false;
    }

    private int logBase3(int n) {
        return (int)(Math.log(n) / Math.log(3));
    }

    public boolean solution(int n) {
        // Honestly using this instead of the for loop since this is O(1)
        int power = logBase3(n);

        while (n > 0) {
            if (n >= Math.pow(3, power)) {
                n -= (int)Math.pow(3, power);
            }

            if (n >= Math.pow(3, power)) {
                return false;
            }

            power--;
        }

        // We know that n is now 0
        return true;
    }
}