package hiperheuristica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Marcel
 */
public class PieceList implements Iterable<Piece> {

  private List<Piece> pieces;
  private Piece biggest = null;
  private int piecesArea = 0;

  public PieceList() {
    this.pieces = new ArrayList<>();
  }

  /**
   * Gets the Piece at the given index
   *
   * @param index of the Piece
   * @return the Piece at the given index
   */
  public Piece get(int index) {
    return this.pieces.get(index);
  }

  /**
   * Removes a given piece
   *
   * @param piece to remove
   * @return true if the piece was found and removed, false otherwise.
   */
  public boolean remove(Piece piece) {
    boolean removed = this.pieces.remove(piece);
    this.piecesArea -= piece.getArea();

    if (this.pieces.size() > 0) {
      if (piece == this.biggest) {
        this.biggest = Collections.max(this.pieces);
      }
    } else {
      this.biggest = null;
    }

    return removed;
  }

  /**
   * Adds a Piece to this PieceList
   *
   * @param piece to add
   */
  public void add(Piece piece) {
    if (this.biggest == null || piece.getArea() > this.biggest.getArea()) {
      this.biggest = piece;
    }

    this.piecesArea += piece.getArea();
    this.pieces.add(piece);
  }

  /**
   * Gets the number of items in this PieceList
   *
   * @return the number of items in this PieceList
   */
  public int size() {
    return this.pieces.size();
  }

  /**
   * Sorts this PieceList according to a specified order
   *
   * @param sort according to an order
   */
  public void sort(Order order) {
    java.util.Collections.<Piece>sort(this.pieces);
    if (order.equals(Order.DESCENDING)) {
      java.util.Collections.<Figure>reverse(this.pieces);
    }
  }

  /**
   * Gets the biggest Piece in this PieceList.
   *
   * @return the biggest Piece. Gives null if the PieceList is empty.
   */
  public Piece getBiggest() {
    return this.biggest;
  }

  /**
   * Determines if all pieces in this PieceList are bigger than the
   * sizeThreshold
   *
   * @param sizeThreshold to compare all pieces against
   * @return true if all pieces are bigger than sizeThreshold, false otherwise.
   */
  public boolean areAllBiggerThan(int sizeThreshold) {
    for (Piece piece : this.pieces) {
      if (piece.getArea() <= sizeThreshold) {
        return false;
      }
    }

    return true;
  }

  /**
   * Gets the area occupied by all the pieces in this list.
   *
   * @return the area occupied by all the pieces.
   */
  public int piecesArea() {
    return this.piecesArea;
  }

  @Override
  public Iterator<Piece> iterator() {
    return this.pieces.iterator();
  }
}
