package org.pethotel.HeavenForPets.domein;

/**
 * Created by Paulina on 2017-10-21.
 */
public class Client {
    private Long id;
    private String firstName;
    private String lastName;
    private int petNumbers;
    private int wholePrice;

    public Client() {
    }

    public int getWholePrice() {
        return wholePrice;
    }

    public void setWholePrice(int wholePrice) {
        this.wholePrice = wholePrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPetNumbers() {
        return petNumbers;
    }

    public void setPetNumbers(int petNumbers) {
        this.petNumbers = petNumbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (petNumbers != client.petNumbers) return false;
        if (wholePrice != client.wholePrice) return false;
        if (firstName != null ? !firstName.equals(client.firstName) : client.firstName != null) return false;
        return lastName != null ? lastName.equals(client.lastName) : client.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + petNumbers;
        result = 31 * result + wholePrice;
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", petNumbers=" + petNumbers +
                ", wholePrice=" + wholePrice +
                '}';
    }
}
