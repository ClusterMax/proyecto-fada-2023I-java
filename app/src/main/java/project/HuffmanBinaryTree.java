/**
 * Clase que implementa un arbol binario de Huffman
 * @Author: <Estudiantes>
 * @Version: <1>
*/

package project;

public class HuffmanBinaryTree {
  private Object key;
  private int weight;
  private HuffmanBinaryTree left;
  private HuffmanBinaryTree right;

  public HuffmanBinaryTree(Object key) {
    this.key = key;
  }

  /**
   * Retorna el valor de la llave, si es un string retorna -1, si es un numero retorna el numero.
   * @return valor de la llave
   */

  public int getNumberKey() {
    if (key instanceof Integer) {
      return (int) key;
    } else if (key instanceof Character) {
      return (char) key;
    } else {
      throw new UnsupportedOperationException("Tipo de clave no admitido");
    }
  }

  public HuffmanBinaryTree getLeft() {
    return left;
  }
  /**
   * Retorna el hijo izquierdo del arbol.
   * @return hijo izquierdo del arbol
   */
  public void setLeft(HuffmanBinaryTree left) {
    this.left = left;
  }
  /**
   * Retorna el hijo derecho del arbol.
   * @return hijo derecho del arbol
   */
  public HuffmanBinaryTree getRight() {
    return right;
  }

  public void setRight(HuffmanBinaryTree right) {
    this.right = right;
  }

  public boolean isLeaf() {
    return left == null && right == null;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }
}
