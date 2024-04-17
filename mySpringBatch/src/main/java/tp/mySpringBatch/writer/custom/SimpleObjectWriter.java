package tp.mySpringBatch.writer.custom;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class SimpleObjectWriter implements ItemWriter<Object>{
	
	
	public SimpleObjectWriter() {
	}
	
	@Override
	public void write(Chunk<? extends Object> chunk) throws Exception {
		for(Object intObj : chunk) {
			System.out.println("OBLECT="+intObj);
		}
	}

	

	
}
