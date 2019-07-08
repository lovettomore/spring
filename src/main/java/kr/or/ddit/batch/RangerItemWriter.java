package kr.or.ddit.batch;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

//프로세스에서 어떤타입을 넘겨받았는지 제네릭으로 쓰면 됩니당
public class RangerItemWriter implements ItemWriter<String> {

	private static final Logger logger = LoggerFactory.getLogger(RangerItemWriter.class);
	
	@Override
	public void write(List<? extends String> items) throws Exception {
		logger.debug("write log ::::::::::: {}", items);
	}

}
