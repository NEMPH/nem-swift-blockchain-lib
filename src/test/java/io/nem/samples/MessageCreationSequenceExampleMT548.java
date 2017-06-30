
package io.nem.samples;

import com.prowidesoftware.swift.model.SwiftTagListBlock;
import com.prowidesoftware.swift.model.field.Field20C;
import com.prowidesoftware.swift.model.field.Field23G;
import com.prowidesoftware.swift.model.mt.mt5xx.MT548;

// TODO: Auto-generated Javadoc
/**
 * Example of message creation using a specific MTnnn class, the Sequences API and
 * nested subsequences. 
 * A partial MT548 is created, including a sequence A with inner sequences A1 and A2. 
 * 
 * 
 *
 */
public class MessageCreationSequenceExampleMT548 {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		new MT548().append(MT548.SequenceA.newInstance(
				/* full content of sequence A here 
				 * We create a SwiftTagListBlock to append both field and subsequences */
				new SwiftTagListBlock()
					.append(Field20C.emptyTag())
					.append(Field23G.emptyTag())
					.append(MT548.SequenceA1.newInstance(
							// Sequence A1 contents here
							))
					.append(MT548.SequenceA2.newInstance(
							// Sequence A2 contents here
							))
				));
	}
}
