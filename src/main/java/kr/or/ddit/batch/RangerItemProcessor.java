package kr.or.ddit.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

//제네릭이 두개인데요 리더에서 넘겨주는 타입이랑 여기서 넘겨주는 타입
public class RangerItemProcessor implements ItemProcessor<String, String>{
	
	private static final Logger logger = LoggerFactory.getLogger(RangerItemWriter.class);
	
	@Override
	public String process(String item) throws Exception {
		String itemProcessed = item + "_modified";
		logger.debug("input : {} / ouput : {} log ::::::::: {}", item, itemProcessed);
		return itemProcessed;
	}

}
