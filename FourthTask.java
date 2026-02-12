public static class Contact {
    String nameContact;
    String phoneNumber;
    List<String> phoneLists;
    HashMap<String,List<String>> info;
    public Contact(String nameContact, String phoneNumber) {
        this.nameContact  = nameContact;
        this.phoneNumber = phoneNumber;
        phoneLists = new ArrayList<>();
        info = new HashMap<>();
    }
    public void addContact() {
        if(info.containsKey(this.nameContact)) {phoneLists.add(phoneNumber);}
        else {
            phoneLists.add(phoneNumber);
            info.put(nameContact,phoneLists);
        }
    }
    public void searchNameContact(String targetName) {
        for(String findName : info.keySet()) {
            if(Objects.equals(findName,targetName)) {
                System.out.println(info.get(findName));
            }
        }
    }
    public void removeNameContact(String targetName) {
        for(String findName : info.keySet()) {
            if(Objects.equals(findName,targetName)) {
                info.remove(findName);
            }
        }
    }
    public void searchPhoneContact(String targetPhone) {
        for(String specifyKey : info.keySet()) {
            for(String findPhone : info.get(specifyKey)) {
                if(Objects.equals(findPhone, targetPhone)) {
                    System.out.println(info.get(specifyKey));
                }
            }
        }
    }
    public void exportTo() {
        try(BufferedWriter writeToFile = new BufferedWriter(new FileWriter("Contacts.txt"))) {
            for(String contacts : info.keySet()) {
                writeToFile.write(contacts + " " + info.get(contacts));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void importFrom() {
        try(BufferedReader readFromFile = new BufferedReader(new FileReader("Contacts.txt"))) {
            System.out.println(readFromFile.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

void main() {
    Contact contact = new Contact("Jack","102-478-222-000");
    contact.addContact();
    contact.searchNameContact("Jack");
    contact.searchPhoneContact("102-478-222-000");
    contact.exportTo();
    contact.importFrom();
    contact.removeNameContact("Jack");
}
