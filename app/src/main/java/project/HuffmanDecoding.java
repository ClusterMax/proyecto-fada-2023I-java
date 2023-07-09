/**
 * Clase de decodificacion de Huffman
 * Esta clase se encarga de decodificar un texto en base a un arbol de huffman
 * @Author: <Kahyberth Steven Gonzales, Carlos Eduardo Guerrero>
 * @Version: <1>
 */
package project;

public class HuffmanDecoding {
  /**
   * Decodifica un texto en base a un árbol de Huffman.
   * @param text texto a decodificar
   * @param tree árbol de Huffman
   * @return texto decodificado
   */
  public String decode(String text, HuffmanBinaryTree tree) {
    StringBuilder decodedText = new StringBuilder();
    HuffmanBinaryTree current = tree;

    for (char c : text.toCharArray()) {
      if (c == '0') {
        current = current.getLeft();
      } else if (c == '1') {
        current = current.getRight();
      }

      if (current.isLeaf()) {
        decodedText.append(current.getNumberKey());
        current = tree;
      }
    }

    return decodedText.toString();
  }
}

