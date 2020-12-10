public class ReverseConcatString {


        public String reverseConcatinateString(String S1, String S2) {
            StringBuilder sb = new StringBuilder();
            for (int i = S1.length() - 1; i >= 0; i--) {
                sb.append(S1.charAt(i));

            }
            for (int i = S2.length() - 1; i >= 0; i--) {
                sb.append(S2.charAt(i));

            }
            return sb.toString();

        }
    }

