import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Objects;

public class BeginningFrame extends JFrame {
    private final Container bF = getContentPane();

    public JLabel gameTitle;

    public JPanel Menu;

    public Player player;

    public Computer computer;

    public GameTable gameTable;

    public JScrollPane mySide;

    public JScrollPane otherSide;

    public static void main(String[] args) {
        new BeginningFrame();
    }

    public BeginningFrame() {
        setTitle("JiaJianChengChu");
        setBounds(550, 100, 800, 800);
        bF.setBackground(new Color(0xFFFFFF));

        gameTitle = initTitle();
        Menu = new Menu();
//        backButton = new BackButton();

        bF.add(gameTitle);
        bF.add(Menu);
//        bF.add(backButton);
        bF.setLayout(null);

        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private JLabel initTitle() {
        URL titleURL = BeginningFrame.class.getResource("title.jpg");
        ImageIcon titleIcon = new ImageIcon(Objects.requireNonNull(titleURL));
        JLabel title = new JLabel(titleIcon);
        title.setBounds(200, 50, 400, 200);
        return title;
    }

    private class Menu extends JPanel {
        public Menu() {
            super();
            JButton pvc = new menuButton("P V C");
            JButton tutorial = new menuButton("Tutorial");
//            JButton cvc = new menuButton("C V C");
//            JButton statistics = new menuButton("Statistics");

            setBounds(200, 400, 400, 200);
            setLayout(new GridLayout(2, 1, 10, 10));

            add(pvc);
//            add(cvc);
            add(tutorial);
//            add(statistics);
        }

        private class menuButton extends JButton {
            public menuButton(String text) {
                super(text);
                Font myFont = new Font("Consolas", Font.BOLD, 30);
                setFont(myFont);
                addActionListener(new menuActionListener());
            }

            private class menuActionListener implements ActionListener {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton mB = (JButton) e.getSource();
                    switch (mB.getText()) {
                        case "Tutorial":
                            new tutorial();
                            break;
                        case "P V C":
                            pvc();
                            break;
                        default:
                            break;
                    }
                }
            }

            private class tutorial extends JDialog {
                public tutorial() {
                    super();
                    setVisible(true);
                    add(new tuPanel());
                    setBounds(450, 400, 1000, 200);
                }

                private class tuPanel extends JPanel {
                    public tuPanel() {
                        super();
                        setVisible(true);
                        setLayout(new GridLayout(4, 1));
                        add(new tuLabel("从1－13各四张一副牌，开局抽5张。\n"));
                        add(new tuLabel("使用一张或者两张牌通过加减乘除四则运算计算凑出牌堆顶部的牌大小则可打出，\n"));
                        add(new tuLabel("若手牌无法凑出牌堆顶牌，则摸牌，直到可以打出，当牌打光时获胜\n"));
                        add(new tuLabel("                                      " +
                                "--coward"));
                    }

                    private class tuLabel extends JLabel {
                        public tuLabel(String text) {
                            super(text);
                            Font tipFont = new Font("黑体", Font.PLAIN, 25);
                            setFont(tipFont);
                        }
                    }
                }
            }
        }
    }

    public void pvc() {
        gameTitle.setVisible(false);
        Menu.setVisible(false);


        gameTable = new GameTable();
        player = new Player(gameTable);
        computer = new Computer(gameTable);
        gameTable.setPlayer(player);
        gameTable.setComputer(computer);
        gameTable.add(new BackButton());

        mySide = new JScrollPane();
        mySide.setPreferredSize(new Dimension(1500, 200));
        mySide.getViewport().add(player.getMyCardsGUI());

        otherSide = new JScrollPane();
        otherSide.setPreferredSize(new Dimension(800, 200));
        otherSide.getViewport().add(computer.getMyCardsGUI());

        bF.setLayout(new BorderLayout());
        bF.add(otherSide, BorderLayout.NORTH);
        bF.add(gameTable, BorderLayout.CENTER);
        bF.add(mySide, BorderLayout.SOUTH);
        bF.validate();
        Card firstTop;
        try {
            firstTop =
                    gameTable.getMainCards().onePickedGUI(gameTable.getLeftCards());
            firstTop.setTop();
            gameTable.add(firstTop.getCardButton());
            gameTable.setTop(firstTop);
        } catch (Exception e) {
            new NoCardError();
        }
        PickButton pickButton = new PickButton();
        gameTable.add(pickButton);
        ResetButton resetButton = new ResetButton();
        gameTable.add(resetButton);
    }

    private class PickButton extends JButton {
        public PickButton() {
            super();
            Font pickFond = new Font("Consolas", Font.BOLD, 20);
            setFont(pickFond);
            setText("Pick one");
            setBounds(650, 150, 130, 60);
            addActionListener(new PickActionListener());
        }

        private class PickActionListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    checkMain(gameTable);
                    Card newCard;
                    newCard = gameTable.getMainCards().onePickedGUI(player.myCards);
                    newCard.showCard();
                    player.myCardsGUI.add(newCard.getCardButton());
                    player.myCardsGUI.validate();
                } catch (Exception ex) {
                    new NoCardError();
                }
            }
        }
    }

    private class BackButton extends JButton {
        public BackButton() {
            super();
            setText("Back");
            Font backFond = new Font("Consolas", Font.BOLD, 20);
            setFont(backFond);
            setBounds(650, 50, 130, 60);
            addActionListener(e -> {
                JButton bB = (JButton) e.getSource();
                gameTitle.setVisible(true);
                Menu.setVisible(true);
                bB.setVisible(false);

                player.myCardsGUI.setVisible(false);
                computer.myCardsGUI.setVisible(false);

                mySide.setVisible(false);
                otherSide.setVisible(false);

                gameTable.setVisible(false);
                bF.setBackground(Color.white);
            });
        }
    }

    private class ResetButton extends JButton {
        public ResetButton() {
            super();
            setText("Reset");
            Font ReFond = new Font("Consolas", Font.BOLD, 20);
            setFont(ReFond);
            setBounds(10, 250, 130, 60);
            addActionListener(e -> {
                gameTable.setCard1(null);
                gameTable.setCard2(null);
                for (Card item : player.getMyCards()) {
                    item.showCard();
                }
            });
        }
    }


    public static class NoCardError extends JDialog {
        public NoCardError() {
            super();
            setVisible(true);
            Font errorFond = new Font("Consolas", Font.BOLD, 25);
            JLabel error = new JLabel("There is no card in the mainCards...Please restart the game!");
            error.setFont(errorFond);
            add(error);
            setBounds(450, 400, 1000, 200);
        }
    }

    public static void checkMain(GameTable gameTable) throws Exception{
        Cards main = gameTable.getMainCards();
        Cards left = gameTable.getLeftCards();
        if (main.isEmpty()) {
            if (left.size() == 1) {
                throw new Exception();
            } else {
                main.addAll(left);
                left.removeIf(item -> !item.equals(gameTable.getTop()));
            }
        }
    }

}
