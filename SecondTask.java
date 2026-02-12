public static class UniqueWords {
    HashSet<String> hashSet;
    TreeSet<String> treeSet;
    HashMap<String,Integer> hashMap;
    String word;
    int frequency;
    public UniqueWords(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
        hashSet = new HashSet<>();
        treeSet = new TreeSet<>();
        hashMap = new HashMap<>();
    }
    public void add() {
        hashSet.add(word);
        treeSet.add(word);
        hashMap.put(word,frequency);
    }
    public int countUniqueWords() {
        return hashSet.size();
    }
    public void allUniqueWords() {
        treeSet.forEach(System.out::println);
    }
    public int countFrequencyWords() {
        return hashMap.size();
    }
}

void main() {
    UniqueWords uw = new UniqueWords("Word",1);
    uw.add();
    System.out.println(uw.countUniqueWords());
    System.out.println(uw.countFrequencyWords());
    uw.allUniqueWords();
}
