

package reader;

import java.io.IOException;

public interface ItemReader<T> {
	public T readLine() throws IOException;
}