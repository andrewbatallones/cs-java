package leetcode;

class PowerofThree {
    public static void main(String[] args) {
        PowerofThree example = new PowerofThree();

        // System.out.println(example.checkPowersOfThree(21));
        // System.out.println(example.checkPowersOfThree(1));
        System.out.println(example.solution(6574365));
    }

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