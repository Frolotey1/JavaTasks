import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

enum Suit { HEARTS, DIAMONDS, CLUBS, SPADES }
enum Rank { TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE }

public class Blackjack {
    static void main() {
        List<Card> deck = Arrays.stream(Suit.values()).flatMap(s -> Arrays.stream(Rank.values()).map(r -> new Card(s, r)))
                .collect(Collectors.toList());
        Collections.shuffle(deck);

        List<List<Card>> players = IntStream.range(0, 2).mapToObj(i -> deck.subList(i * 2, (i + 1) * 2)).toList();

        players.stream().max(Comparator.comparingInt(
                hand -> hand.stream().mapToInt(c -> getValue(c.getRank())).sum()))
                .ifPresent(winner -> System.out.println("Winner: " + winner));
    }

    static int getValue(Rank rank) { 
        return (rank == Rank.JACK || 
                rank == Rank.QUEEN || 
                rank == Rank.KING) ? 10 : (rank == Rank.ACE ? 11 : rank.ordinal() + 1); 
    }

    static class Card {
        Suit suit; Rank rank; 
        Card(Suit suit, Rank rank) { this.suit = suit; this.rank = rank; } 
        Rank getRank() { return rank; }
    }
}
