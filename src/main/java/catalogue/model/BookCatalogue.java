package catalogue.model;

public class BookCatalogue {
	private String ISBN;
	private String title;
	private int price;
	private String edition;
	private String category;
	private String publisherId;
	private String authorNo;
	private String bookInfo;
	private String publishedYear;
	private int count;
	
	public BookCatalogue() {
	}
	
	public BookCatalogue(String ISBN, String title, int price, String edition, String category, String publisherId,
			String authorNo, String bookInfo, String publishedYear, int count) {
		super();
		this.ISBN = ISBN;
		this.title = title;
		this.price = price;
		this.edition = edition;
		this.category = category;
		this.publisherId = publisherId;
		this.authorNo = authorNo;
		this.bookInfo = bookInfo;
		this.publishedYear = publishedYear;
		this.count = count;
	}
	
	public BookCatalogue(String iSBN, String title) {
		super();
		ISBN = iSBN;
		this.title = title;
	}
	
	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}

	public String getAuthorNo() {
		return authorNo;
	}

	public void setAuthorNo(String authorNo) {
		this.authorNo = authorNo;
	}

	public String getBookInfo() {
		return bookInfo;
	}

	public void setBookInfo(String bookInfo) {
		this.bookInfo = bookInfo;
	}

	public String getPublishedYear() {
		return publishedYear;
	}

	public void setPublishedYear(String publishedYear) {
		this.publishedYear = publishedYear;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}