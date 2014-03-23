/**
 *
 */
package net.aldente_studio.java;

import gnu.getopt.Getopt;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

/**
 * @author koji
 *
 */
public class Files2map {

    /**
     *
     */
    public Files2map() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Getopt options = new Getopt("Files2map", args, "-c:");
        int c;
        String path = "";
        String configFile = "";
        while ((c = options.getopt()) != -1) {
            switch (c) {
            case 'c':
                configFile = options.getOptarg();
                break;
            case 1:
                path = options.getOptarg();
                break;
            }
        }
        System.out.printf("Target path is '%s'.%n", path);
        System.out.printf("Target config file is '%s'.%n", configFile);
        // ---
        try {
            File fileObject = new File(configFile);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // 妥当性検証を行う
            //factory.setValidating(true);
            // 名前空間を認識する
            //factory.setNamespaceAware(true);
            // XML Scehmaを認識する
            //factory.setAttribute("http://apache.org/xml/features/validation/schema", true);
            // DOM Documentインスタンス用ファクトリの生成
            DocumentBuilder docBuilder;
            docBuilder = factory.newDocumentBuilder();
            Document document = docBuilder.parse(fileObject);
            showChild(document, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void showChild(Node obj, int level) {
        Node node;
        String str;
        int i;

        node = obj;
        while (true) {
            // 階層レベルの表示
            str = "";
            for (i = 0; i < level; i++) {
                str += '+';
            }

            // ノード名の表示
            str += node.getNodeName();

            // ノード値の表示
            str += ", ";
            if (node.getNodeValue() != null) {
                str += node.getNodeValue().trim();
            }

            // ノードタイプの表示
            str += ", ";
            str += node.getNodeType();

            // 属性ノードの有無表示
            if (node.hasAttributes()) {
                str += ", ATTR";
            }

            // ノード情報の表示
            System.out.println(str);

            // 子ノードの表示（再帰）
            if (node.hasChildNodes()) {
                showChild(node.getFirstChild(), level + 1);
            }

            node = node.getNextSibling();
            if (node == null) {
                break;
            }
        }
        return;
    }

}


