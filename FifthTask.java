private static final int NUM_ELEMENTS = 100000;

void main() {
    testCollection("ArrayList");
    testCollection("LinkedList");
    testCollection("HashSet");
    testCollection("TreeSet");
    testCollection("HashMap");
    testCollection("TreeMap");
}

static void testCollection(String collectionType) {
    List<Integer> data = new ArrayList<>();
    for (int i = 0; i < NUM_ELEMENTS; i++) {
        data.add(new Random().nextInt(NUM_ELEMENTS));
    }

    long startTime, endTime;
    double addTime = 0, searchTime = 0, deleteTime;

    startTime = System.nanoTime();
    Collection<Integer> collection;
    switch (collectionType) {
        case "ArrayList":
            collection = new ArrayList<>(data);
            break;
        case "LinkedList":
            collection = new LinkedList<>(data);
            break;
        case "HashSet":
            collection = new HashSet<>(data);
            break;
        case "TreeSet":
            collection = new TreeSet<>(data);
            break;
        case "HashMap":
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < NUM_ELEMENTS; i++) {
                map.put(i, data.get(i));
            }
            collection = map.keySet();
            break;
        case "TreeMap":
            Map<Integer, Integer> treeMap = new TreeMap<>();
            for (int i = 0; i < NUM_ELEMENTS; i++) {
                treeMap.put(i, data.get(i));
            }
            collection = treeMap.keySet();
            break;
        default:
            throw new IllegalArgumentException("Неверный тип коллекции: " + collectionType);
    }

    int midIndex = NUM_ELEMENTS / 2;
    if (collectionType.equals("ArrayList") || collectionType.equals("LinkedList")) {
        ((List<Integer>) collection).get(midIndex);
    } else if (collectionType.equals("HashSet") || collectionType.equals("TreeSet")) {
        collection.contains(data.get(midIndex));
    } else if (collectionType.equals("HashMap") || collectionType.equals("TreeMap")) {
        collection.contains(data.get(midIndex));
    }

    startTime = System.nanoTime();
    for (int i = 10; i < NUM_ELEMENTS; i += 10) {
        switch (collectionType) {
            case "ArrayList", "LinkedList" -> ((List<Integer>) collection).remove(i);
            case "HashSet", "TreeSet" -> collection.remove(data.get(i));
            case "HashMap", "TreeMap" -> ((Map<Integer, Integer>) collection).remove(i);
        }
    }
    endTime = System.nanoTime();

    deleteTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

    System.out.println("All time of working all collections: " + (endTime - startTime));

    System.out.printf("| %-12s | %-14.2f | %-9.2f | %-12.2f |\n",
            collectionType, addTime, searchTime, deleteTime);
}

