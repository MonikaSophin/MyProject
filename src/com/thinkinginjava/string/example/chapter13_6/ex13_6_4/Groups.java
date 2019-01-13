package com.thinkinginjava.string.example.chapter13_6.ex13_6_4;

import java.util.regex.*;

public class Groups {
  static public final String POEM =
    "Twas brillig, and the slithy toves\n" +
    "Did gyre and gimble in the wabe.\n" +
    "All mimsy were the borogoves,\n" +
    "And the mome raths outgrabe.\n\n" +
    "Beware the Jabberwock, my son,\n" +
    "The jaws that bite, the claws that catch.\n" +
    "Beware the Jubjub bird, and shun\n" +
    "The frumious Bandersnatch.";
  public static void main(String[] args) {
    //(?m)为开头的模式标记。
    Matcher m = Pattern.compile("(?m)(\\S+)\\s+((\\S+)\\s+(\\S+))$").matcher(POEM);
    while(m.find()) {
      for(int j = 0; j <= m.groupCount(); j++)
        System.out.print("[" + m.group(j) + "]");
      System.out.println();
    }
  }
}
/**output:
 * [the slithy toves][the][slithy toves][slithy][toves]
 * [in the wabe.][in][the wabe.][the][wabe.]
 * [were the borogoves,][were][the borogoves,][the][borogoves,]
 * [mome raths outgrabe.][mome][raths outgrabe.][raths][outgrabe.]
 * [Jabberwock, my son,][Jabberwock,][my son,][my][son,]
 * [claws that catch.][claws][that catch.][that][catch.]
 * [bird, and shun][bird,][and shun][and][shun]
 * [The frumious Bandersnatch.][The][frumious Bandersnatch.][frumious][Bandersnatch.]
 */
