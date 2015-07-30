package xml;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.DOMBuilder;
import org.xml.sax.SAXException;

import pdf2table.AlignmentTableBuilder;
import pdf2table.DomainTableBuilder;
import pdf2table.TableAreaScanner;
import pdf2table.TableBuilder;
import data.Font;
import data.InvoicePage;
import data.Location;
import data.TableArea;
import data.Text;
import data.Token;

public class InvoiceXMLParser {

	private String xmlFilePath;

	public InvoiceXMLParser(String path) {
		xmlFilePath = path;
	}

	public InvoicePage parse() throws ParserConfigurationException,
			SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dombuilder = factory.newDocumentBuilder();
		org.w3c.dom.Document w3cDocument = dombuilder.parse(xmlFilePath);
		DOMBuilder jdomBuilder = new DOMBuilder();
		Document jdomDocument = jdomBuilder.build(w3cDocument);

		Element root = jdomDocument.getRootElement();
		// Get the first page
		Element page = root.getChild("PAGE");
		InvoicePage iPage = createPage(page);

		// Get the text elements
		List<Element> textList = page.getChildren("TEXT");
		Text iText = null;
		for (Element text : textList) {
			iText = createText(text);
			for (Element token : text.getChildren()) {
				Token iToken = createToken(token);
				iText.addToken(iToken);
			}
			iText.setType();
			iPage.addText(iText);
		}

		return iPage;
	}

	private Token createToken(Element token) {
		Token iToken = new Token();
		iToken.setLocation(createLocation(token));
		iToken.setFont(createFont(token));
		iToken.setText(token.getText());
		return iToken;
	}

	private Font createFont(Element elem) {
		Font iFont = new Font();
		iFont.setBold(elem.getAttributeValue("bold").equalsIgnoreCase("yes"));
		iFont.setColor(elem.getAttributeValue("font-color"));
		iFont.setItalic(elem.getAttributeValue("italic")
				.equalsIgnoreCase("yes"));
		iFont.setName(elem.getAttributeValue("font-name"));
		if (elem.getAttribute("serif") != null)
			iFont.setSerif(elem.getAttributeValue("serif").equalsIgnoreCase(
					"yes"));
		iFont.setSize(Float.parseFloat(elem.getAttributeValue("font-size")));
		if (elem.getAttribute("symbolic") != null)
			iFont.setSymbolic(elem.getAttributeValue("symbolic")
					.equalsIgnoreCase("yes"));
		return iFont;
	}

	private Location createLocation(Element elem) {
		Location loc = new Location();
		loc.setX(elem.getAttributeValue("x"));
		loc.setY(elem.getAttributeValue("y"));
		loc.setHeight(elem.getAttributeValue("height"));
		loc.setWidth(elem.getAttributeValue("width"));
		return loc;
	}

	private Text createText(Element text) {
		Text iText = new Text();
		iText.setLocation(createLocation(text));
		return iText;
	}

	private InvoicePage createPage(Element page) {
		InvoicePage iPage = new InvoicePage();
		Location loc = new Location();
		loc.setHeight(page.getAttributeValue("height"));
		loc.setWidth(page.getAttributeValue("width"));
		iPage.setLocation(loc);
		return iPage;
	}

	public static void main(String[] args) {
		final String ex1 = "C:\\Users\\akulkarni\\Documents\\Yodlee\\Garage Fest\\invoice\\R4\\retail invoice.xml";
		final String ex2 = "C:/Users/akulkarni/Documents/Yodlee/Garage Fest/invoice/p2.xml";
		String ex3 = "C:/Users/akulkarni/Documents/Yodlee/Garage Fest/invoice/walmart/w4.xml";
		if (args != null && args.length == 1) {
			// System.out.println(args[0]);
			ex3 = args[0];
		}
		InvoiceXMLParser parser = new InvoiceXMLParser(ex3);
		try {
			InvoicePage iPage = parser.parse();
			iPage.sortTexts();
			iPage.inferNeighborhood();
			TableAreaScanner tableScanner = new TableAreaScanner(iPage);
			TableArea tArea = tableScanner.scan();
			System.out.println("Number of textboxes in the table Area : "
					+ tArea.getTableTexts().size());
			TableBuilder tBuilder = new DomainTableBuilder(tArea);
			// TableBuilder tBuilder = new AlignmentTableBuilder(tArea);
			tBuilder.buildTable();
			// tArea.print();
			// iPage.findText("Qty").getFirst().print();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
