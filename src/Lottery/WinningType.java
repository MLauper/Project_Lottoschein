package Lottery;

public enum WinningType {
    FIVE_WITH_TWO_STARS  ("5 + ☆ ☆ \t:"),
    FIVE_WITH_ONE_STAR   ("5 + ☆   \t:"),
    FIVE_WITH_ZERO_STARS ("5       \t:"),
    FOUR_WITH_TWO_STARS  ("4 + ☆ ☆ \t:"),
    FOUR_WITH_ONE_STAR   ("4 + ☆   \t:"),
    FOUR_WITH_ZERO_STARS ("4       \t:"),
    THREE_WITH_TWO_STARS ("3 + ☆ ☆ \t:"),
    THREE_WITH_ONE_STAR  ("3 + ☆   \t:"),
    THREE_WITH_ZERO_STARS("3       \t:"),
    TWO_WITH_TWO_STARS   ("2 + ☆ ☆ \t:"),
    TWO_WITH_ONE_STAR    ("2 + ☆   \t:"),
    TWO_WITH_ZERO_STARS  ("2       \t:"),
    ONE_WITH_TWO_STARS   ("1 + ☆ ☆ \t:"),
    ONE_SUPER_STAR       ("1 Super☆\t:"),
    TWO_SUPER_STAR       ("2 Super☆\t:"),
    THREE_SUPER_STAR     ("3 Super☆\t:"),
    FOUR_SUPER_STAR      ("4 Super☆\t:"),
    FIVE_SUPER_STAR      ("5 Super☆\t:");
    
    
    private String text;

    // Constructor 
    WinningType(String t) {
      text = t;
    }

    public String getText() {
      return text;
    }
}
