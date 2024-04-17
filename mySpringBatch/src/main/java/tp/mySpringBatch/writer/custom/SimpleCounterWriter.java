package tp.mySpringBatch.writer.custom;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class SimpleCounterWriter implements ItemWriter<Integer>{
	
	
	public SimpleCounterWriter() {
	}
	
	@Override
	public void write(Chunk<? extends Integer> chunk) throws Exception {
		for(Integer intObj : chunk) {
			System.out.println("COUNTER="+intObj);
		}
	}

	

	
}
