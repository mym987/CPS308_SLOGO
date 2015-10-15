package parser;

import java.io.Closeable;
import java.util.NoSuchElementException;

public interface Tokenizer extends Closeable {

	/**
	 * Returns true if this scanner has another token in its input.
	 *
	 * @return true if and only if this scanner has another token
	 * @throws IllegalStateException
	 *             if this tokenizer is closed
	 */
	public boolean hasNext();

	/**
	 * Finds and returns the next complete token from this tokenizer.
	 * 
	 * @throws NoSuchElementException
	 *             if no more tokens are available
	 * @throws IllegalStateException
	 *             if this tokenizer is closed
	 * @return the next token
	 */
	public String next();

	/**
	 * Closes this tokenizer.
	 *
	 * <p>
	 * Attempting to perform search operations after a tokenizer has been closed
	 * will result in an {@link IllegalStateException}.
	 *
	 */
	public void close();
}
