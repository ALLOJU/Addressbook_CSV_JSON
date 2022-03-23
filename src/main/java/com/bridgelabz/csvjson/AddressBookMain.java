package com.bridgelabz.csvjson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class AddressBookMain {
	public static ArrayList<Address> persons;
	public HashMap<String, Address> persons_a;
	public HashMap<String, Address> persons_b;

	Scanner sc = new Scanner(System.in);

	public AddressBookMain() {
		persons = new ArrayList<Address>();
		persons_a = new HashMap<>();
		persons_b = new HashMap<>();
	}
	/**
	 * Method to write data to json file using gson library
	 * @param addresBook --list of contacts
	 * @return -size of the list
	 * @throws IOException -throws IOException when input file is not exist
	 */
	public static int writeJson(ArrayList<Address> addresBook) throws IOException {
		Gson gson = new GsonBuilder().create();
		List<Address> list = addresBook.stream().collect(Collectors.toList());
		String json = gson.toJson(list);
		FileWriter writer = new FileWriter("C:\\Users\\nani\\Documents\\Java Programs\\AddressBook_CSV_JSON\\src\\test\\resources\\jsonOfAddressBook.json");
		writer.write(json);
		writer.close();
		return list.size();
	}
	/**
	 * Method to read json data from the address book list
	 * @return
	 */
	public static int readJson() {
		int count = 0;
		try {
			Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\nani\\Documents\\Java Programs\\AddressBook_CSV_JSON\\src\\test\\resources\\jsonOfAddressBook.json"));
			List<Address> addresBook = new Gson().fromJson(reader, new TypeToken<List<Address>>() {}.getType());
			Iterator<Address> jsonIterator = addresBook.iterator();

			while (jsonIterator.hasNext()) {
				count++;
				Address adressBook = jsonIterator.next();
				System.out.println("First Name : " + adressBook.getFirstname());
				System.out.println("Last Name : " + adressBook.getLastname());
				System.out.println("Address: " + adressBook.getAddress());
				System.out.println("City : " + adressBook.getCity());
				System.out.println("State : " + adressBook.getState());
				System.out.println("Zip : " + adressBook.getZip());
				System.out.println("Number : " + adressBook.getNumber());
				System.out.println("Email : " + adressBook.getEmail());
				System.out.println("*****************");
			}
			reader.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}

	/**
	 * Method to write contact details to csv file
	 * @param addressBook - list of contacts
	 * @return
	 * @throws IOException - throws exception when file is not exist
	 */
	public static int writeCsv(ArrayList<Address> addressBook) throws IOException {
		int count = 0;
		try (Writer writer = Files.newBufferedWriter(Paths.get("C:\\Users\\nani\\Documents\\Java Programs\\AddressBook_CSV_JSON\\src\\test\\resources\\csvOfAddressBook.csv"));) {
			StatefulBeanToCsv<Address> beanToCsv = new StatefulBeanToCsvBuilder<Address>(writer).build();

			ArrayList<Address> adressBook = new ArrayList<Address>();
			for (Address addr : addressBook) {
				adressBook.add(addr);
				count++;
			}
			beanToCsv.write(adressBook);
		} catch (CsvRequiredFieldEmptyException e) {
			e.printStackTrace();
		} catch (CsvDataTypeMismatchException e) {
			e.printStackTrace();
		}
		return count;

	}

	/**
	 * Method to read Comma separated data file data
	 * @return
	 * @throws IOException
	 */
	public static int readCsv() throws IOException {
		int count = 0;
		try (Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\nani\\Documents\\Java Programs\\AddressBook_CSV_JSON\\src\\test\\resources\\csvOfAddressBook.csv"));) {
			CsvToBean<Address> csvToBean = new CsvToBeanBuilder<Address>(reader).withType(Address.class).build();
			Iterator<Address> csvUserIterator = csvToBean.iterator();

			while (csvUserIterator.hasNext()) {
				count++;
				Address adressBook = csvUserIterator.next();
				System.out.println("First Name : " + adressBook.getFirstname());
				System.out.println("Last Name : " + adressBook.getLastname());
				System.out.println("Address: " + adressBook.getAddress());
				System.out.println("City : " + adressBook.getCity());
				System.out.println("State : " + adressBook.getState());
				System.out.println("Zip : " + adressBook.getZip());
				System.out.println("Number : " + adressBook.getNumber());
				System.out.println("Email : " + adressBook.getEmail());
				System.out.println("*****************");
			}
		}
		return count;
	}
	/**
	 * method to write data to normal file which is in the text format
	 * @param persons - List of persons
	 */
	public void writeData(ArrayList<Address> persons) {
		StringBuffer empBuffer = new StringBuffer();
		persons.forEach(employee -> {
			String addressBookDataString = employee.toString().concat("\n");
			empBuffer.append(addressBookDataString);
		});
		try {
			Files.write(Paths.get("address-file.txt"), empBuffer.toString().getBytes());

		} catch (IOException e) {
			e.printStackTrace();

		}
	}
	/**
	 * read data from text file
	 * @param addresslList - list of address book contacts
	 * @return
	 */
	public long readData(ArrayList<Address> addresslList) {

		try {
			Files.lines(new File("address-file.txt").toPath()).map(line -> line.trim())
					.forEach(line -> System.out.println(line));

		} catch (IOException e) {

		}
		return addresslList.size();

	}
	
	public long countEntries() {
		long entries = 0;
		try {
			entries = Files.lines(new File("address-file.txt").toPath()).count();
		} catch (IOException e) {

		}
		return entries;
	}

	public void Finddetails(String city) {

		for (int i = 0; i < persons.size(); i++) {
			Address details = (Address) persons.get(i);

			if (city.equals(details.city)) {
				System.out.println("\n" + details.firstname + " " + details.lastname + " " + details.address + " "
						+ details.city + " " + details.email + " " + details.zip + "\n");
			}

		}

	}

	public static Address Findname(String name) {
		Address findname = new Address();

		for (int i = 0; i < persons.size(); i++) {
			Address c = (Address) persons.get(i);

			if (name.equals(c.firstname)) {
				findname = c;
				System.out.println("\n" + findname.firstname + " " + findname.lastname + " " + findname.address + " "
						+ findname.city + " " + findname.email + " " + findname.zip + "\n");
				break;
			}

		}
		return findname;

	}

	public void FindDetailsbyCitybyMap(String city) {

		Address details = persons_a.get(city);
		System.out.println("\n" + details.firstname + " " + details.lastname + " " + details.address + " "
				+ details.city + " " + details.state + " " + details.email + " " + details.zip + "\n");

	}

	public void FindDetailsbyStatebyMap(String state) {

		Address details = persons_b.get(state);
		System.out.println("\n" + details.firstname + " " + details.lastname + " " + details.address + " "
				+ details.city + " " + details.state + " " + details.email + " " + details.zip + "\n");

	}

	public void AddName() {
		String a, b, z, d, e, f, g, h;
		System.out.println("Enter your details\n");
		System.out.println("First name\n");
		a = sc.nextLine();

		System.out.println("Last name\n");
		b = sc.nextLine();
		for (int i = 0; i < persons.size(); i++) {
			Address name = (Address) persons.get(i);
			if (a.equals(name.firstname) && b.equals(name.lastname)) {
				System.out.println("ohhhhh! Name Already Taken");
				return;
			}
		}
		System.out.println("Address\n");
		z = sc.nextLine();
		System.out.println("City\n");
		d = sc.nextLine();
		System.out.println("State\n");
		e = sc.nextLine();
		System.out.println("Zip\n");
		f = sc.nextLine();
		System.out.println("Phone No.\n");
		g = sc.nextLine();
		System.out.println("Email\n");
		h = sc.nextLine();
		Address c = new Address(a, b, z, d, e, f, g, h);
		System.out.println("First name " + c.firstname + "\n");
		persons.add(c);
		System.out.println("Added succesfully, the Contacts are\n");
		for (int i = 0; i < persons.size(); i++) {
			persons.get(i).Display();
		}

	}

	public void edit(String name) {
		System.out.println("\nIn the Edit Method \n");
		Address c = Findname(name);
		System.out.println(
				"Enter Choice to edit\n1.First Name\n2.Second name\n3.Address\n4.City\n5.State\n6.Zip\n7.Phone\n8.Email\n");
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			System.out.println("Enter new name:");
			c.firstname = sc.nextLine();
			for (int i = 0; i < persons.size(); i++) {
				persons.get(i).Display();
			}
			break;
		case 2:
			System.out.println("Enter last name:");
			c.lastname = sc.nextLine();
			for (int i = 0; i < persons.size(); i++) {
				persons.get(i).Display();
			}
			break;
		case 3:
			System.out.println("Enter new address:");
			c.address = sc.nextLine();
			for (int i = 0; i < persons.size(); i++) {
				persons.get(i).Display();
			}
			break;
		case 4:
			System.out.println("Enter new city:");
			c.city = sc.nextLine();
			for (int i = 0; i < persons.size(); i++) {
				persons.get(i).Display();
			}
			break;
		case 5:
			System.out.println("Enter new state:");
			c.state = sc.nextLine();
			for (int i = 0; i < persons.size(); i++) {
				persons.get(i).Display();
			}
			break;
		case 6:
			System.out.println("Enter new zip:");
			c.zip = sc.nextLine();
			for (int i = 0; i < persons.size(); i++) {
				persons.get(i).Display();
			}
			break;
		case 7:
			System.out.println("Enter new number:");
			c.number = sc.nextLine();
			for (int i = 0; i < persons.size(); i++) {
				persons.get(i).Display();
			}
			break;
		case 8:
			System.out.println("Enter new email:");
			c.email = sc.nextLine();
			for (int i = 0; i < persons.size(); i++) {
				persons.get(i).Display();
			}
			break;
		default:
			break;

		}

	}

	public void delete(String name) {
		for (int i = 0; i < persons.size(); i++) {

			Address c = (Address) persons.get(i);
			if (name.equals(c.firstname)) {
				persons.remove(i);
			}
		}
		for (int i = 0; i < persons.size(); i++) {
			System.out.println("After Deletion");
			persons.get(i).Display();
		}

	}
}
