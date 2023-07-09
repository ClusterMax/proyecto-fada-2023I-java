/**
 * Clase huffmanCoding
 * Esta clase se encarga de codificar un texto en base a un arbol de huffman
 * @Author: <Kahyberth Steven Gonzales, Carlos Eduardo Guerrero>
 * @Version: <1>
 */
package project;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCoding {
  /**
   * Constructor de la clase HuffmanCoding.
   * @param text texto a codificar
   * @return texto codificado
   */
  private HuffmanBinaryTree tree;
  private HashMap<Character, String> table;
  private StringBuilder summary;
  public String encode(String text) {
    // Obtener la frecuencia de cada carácter en el texto
    HashMap<Character, Integer> frequencyMap = new HashMap<>();
    for (char c : text.toCharArray()) {
      frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
    }

    // Construir el árbol de Huffman
    HuffmanBinaryTree huffmanTree = buildHuffmanTree(frequencyMap);

    // Generar la tabla de codificación
    HashMap<Character, String> codeTable = new HashMap<>();
    generateCodeTable(huffmanTree, "", codeTable);

    // Codificar el texto utilizando la tabla de codificación
    StringBuilder encodedText = new StringBuilder();
    for (char c : text.toCharArray()) {
      String code = codeTable.get(c);
      encodedText.append(code);
    }

    generateSummary();

    return encodedText.toString();
  }

  /**
   * Retorna el arbol de huffman.
   * @return arbol de huffman
   */
  public HuffmanBinaryTree getTree() {
    return tree;
  }

  /**
   * Retorna la tabla de codificacion.
   * @return tabla de codificacion
   */
  public HashMap<Character, String> getTable() {
    return table;
  }

  /**
   * Retorna el resumen de la codificacion
   * @return resumen de la codificacion en formato string
   */
  public String getSummary() {
    return summary.toString();
  }

  private HuffmanBinaryTree buildHuffmanTree(HashMap<Character, Integer> frequencyMap) {
    PriorityQueue<HuffmanBinaryTree> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(HuffmanBinaryTree::getWeight));

    for (char c : frequencyMap.keySet()) {
      int frequency = frequencyMap.get(c);
      HuffmanBinaryTree node = new HuffmanBinaryTree(c);
      node.setWeight(frequency);
      priorityQueue.offer(node);
    }

    while (priorityQueue.size() > 1) {
      HuffmanBinaryTree leftNode = priorityQueue.poll();
      HuffmanBinaryTree rightNode = priorityQueue.poll();

      int combinedWeight = leftNode.getWeight() + rightNode.getWeight();
      HuffmanBinaryTree parentNode = new HuffmanBinaryTree(null);
      parentNode.setWeight(combinedWeight);
      parentNode.setLeft(leftNode);
      parentNode.setRight(rightNode);

      priorityQueue.offer(parentNode);
    }

    return priorityQueue.poll();
  }

  private void generateCodeTable(HuffmanBinaryTree node, String code, HashMap<Character, String> table) {
    if (node.isLeaf()) {
      table.put((char) node.getNumberKey(), code);
    } else {
      generateCodeTable(node.getLeft(), code + "0", table);
      generateCodeTable(node.getRight(), code + "1", table);
    }
  }


  private void generateSummary() {
    summary = new StringBuilder();
    generateSummaryRecursive(tree, "");
  }

  private void generateSummaryRecursive(HuffmanBinaryTree node, String indent) {
    if (node == null) {
      return;
    }

    if (node.isLeaf()) {
      summary.append(indent).append("Character: ").append(node.getNumberKey())
              .append(", Frequency: ").append(node.getWeight()).append("\n");
    } else {
      summary.append(indent).append("Internal Node, Frequency: ").append(node.getWeight()).append("\n");
      generateSummaryRecursive(node.getLeft(), indent + "  ");
      generateSummaryRecursive(node.getRight(), indent + "  ");
    }
  }


}