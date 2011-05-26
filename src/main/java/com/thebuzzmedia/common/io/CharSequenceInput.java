/**   
 * Copyright 2011 The Buzz Media, LLC
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.thebuzzmedia.common.io;

import java.io.IOException;

public class CharSequenceInput extends AbstractInput<CharSequence, char[]> {
	public CharSequenceInput(CharSequence source)
			throws IllegalArgumentException {
		this(source, 0, source.length());
	}

	public CharSequenceInput(CharSequence source, int index, int length)
			throws IllegalArgumentException {
		super(source, index, length);

		// Post-verify
		if (length > source.length())
			throw new IllegalArgumentException("length [" + length
					+ "] must be <= source.length() [" + source.length() + "]");
	}

	public int read(char[] buffer, int index) throws IllegalArgumentException,
			IOException {
		if (buffer == null)
			throw new IllegalArgumentException("buffer cannot be null");

		return read(buffer, index, buffer.length);
	}

	@Override
	protected int readImpl(char[] buffer, int index, int length)
			throws IllegalArgumentException, IOException {
		// Verify the buffer bounds
		if ((index + length) > buffer.length)
			throw new IllegalArgumentException("(index + length) ["
					+ (index + length) + "] must be <= buffer.length ["
					+ buffer.length + "]");

		// Do the read op
		for (int i = 0; i < length; i++)
			buffer[index + i] = source.charAt(position + i);

		// Return the amount actually read
		return length;
	}

}
