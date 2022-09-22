package com.logit.one;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.regex.*;


public class UpdateXml {
    public static void main(String[] args) {
        try {
            File file = new File("src/main/resources/VP_XML.xml");

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setIgnoringElementContentWhitespace(true);
            dbf.setValidating(true);
            dbf.setSchema(dbf.getSchema());
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("element");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                System.out.println("\nNode Name :" + node.getNodeType());
                System.out.println("\nNode Name :" + node.ELEMENT_NODE);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;//trueHeading

                    if (element.getElementsByTagName("destinationETA").item(0) != null) {
                        String date = element.getElementsByTagName("destinationETA").item(0).getTextContent();
                        System.out.println(date);
                        String d1 = getTimeFormat(date);
                        element.getElementsByTagName("destinationETA").item(0).setTextContent(d1);
                        String dat = element.getElementsByTagName("destinationETA").item(0).getTextContent();
                        System.out.println(dat);
                        System.out.println("new format" + d1);
                    }
                    if (element.getElementsByTagName("messageDateTime").item(0) != null) {
                        String date1 = element.getElementsByTagName("messageDateTime").item(0).getTextContent();
                        String d2 = getTimeFormat(date1);

                        System.out.println("new format" + d2);

                        element.getElementsByTagName("messageDateTime").item(0).setTextContent(d2);
                        String dat1 = element.getElementsByTagName("messageDateTime").item(0).getTextContent();
                        System.out.println(dat1);
                    }


                    //  System.out.println(newdate);//9-27
                }

            }
            File file1 = new File("src/main/resources/new_xml.xml");
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            DOMSource src = new DOMSource(doc);
            StreamResult res = new StreamResult(file1);
            transformer.transform(src, res);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static String getTimeFormat(String date) {
        System.out.println("date is "+date);
        String newdate = (String) date.subSequence(8, 28);
        newdate = newdate.replace('T', ' ');
        newdate = newdate.replace('-', '/');
        System.out.println("new date is "+newdate);
        String year = newdate.substring(0,4);
        System.out.println("year "+year);
        String month =  newdate.substring(5,7);
        System.out.println("month is "+month);
        String day =  newdate.substring(8,10);
        System.out.println("day is "+day);
        String time =  newdate.substring(11,19);
        String finaldate = day + "/" + month + "/" + year + " " + time;
        // System.out.println(finaldate);
        return finaldate;
    }
}
