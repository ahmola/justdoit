package com.example.react;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.stream.Stream;

@SpringBootTest
class ReactApplicationTests {

	@Test
	void createFlux_just() {
		Flux<String> fruitFlux = Flux
				.just("Apple", "Banana","Watermelon", "Strawberry");
		fruitFlux.subscribe(
				f -> System.out.println(f)
		);

		StepVerifier.create(fruitFlux)
				.expectNext("Apple")
				.expectNext("Banana")
				.expectNext("Watermelon")
				.expectNext("Strawberry")
				.verifyComplete();
	}

	@Test
	void createFlux_fromArray(){
		String[] fruits = new String[]{
				"Apple", "Banana", "Grape", "Watermelon", "Strawberry"
		};

		Flux<String> fruitFlux = Flux.fromArray(fruits);

		StepVerifier.create(fruitFlux)
				.expectNext("Apple")
				.expectNext("Banana")
				.expectNext("Grape")
				.expectNext("Watermelon")
				.expectNext("Strawberry")
				.verifyComplete();
	}

	@Test
	void createFlux_fromIter(){
		List<String> fruits = new ArrayList<>();
		fruits.add("Apple");
		fruits.add("Banana");
		fruits.add("Watermelon");
		fruits.add("Strawberry");

		Flux<String> fruitFlux = Flux.fromIterable(fruits);

		StepVerifier.create(fruitFlux)
				.expectNext("Apple")
				.expectNext("Banana")
				.expectNext("Watermelon")
				.expectNext("Strawberry")
				.verifyComplete();
	}

	@Test
	void createFlux_fromStream(){
		Stream<String> fruits = Stream.of("Apple" , "Banana", "Watermelon", "Strawberry");

		Flux<String> fruitFlux = Flux.fromStream(fruits);

		StepVerifier.create(fruitFlux)
				.expectNext("Apple")
				.expectNext("Banana")
				.expectNext("Watermelon")
				.expectNext("Strawberry")
				.verifyComplete();
	}

	@Test
	void createFlux_range(){
		Flux<Integer> integerFlux = Flux.range(1,5);

		StepVerifier.create(integerFlux)
				.expectNext(1)
				.expectNext(2)
				.expectNext(3)
				.expectNext(4)
				.expectNext(5)
				.verifyComplete();
	}

	@Test
	void createFlux_interval(){
		Flux<Long> intervalFlux =
				Flux.interval(Duration.ofSeconds(1))
						.take(5);

		System.out.println(System.currentTimeMillis());
		StepVerifier.create(intervalFlux)
				.expectNext(0L)
				.expectNext(1L)
				.expectNext(2L)
				.expectNext(3L)
				.expectNext(4L)
				.verifyComplete();
		System.out.println(System.currentTimeMillis());
	}

	@Test
	void mergeFlux(){
		Flux<String> charFlux = Flux
				.just("Chai", "Korsica", "CNMN")
				.delayElements(Duration.ofMillis(500));
		Flux<String> foodFlux = Flux
				.just("Pizza", "Coke")
				.delaySubscription(Duration.ofMillis(250))
				.delayElements(Duration.ofMillis(500));

		Flux<String> mergeFlux = charFlux.mergeWith(foodFlux);

		StepVerifier.create(mergeFlux)
				.expectNext("Chai")
				.expectNext("Pizza")
				.expectNext("Korsica")
				.expectNext("Coke")
				.expectNext("CNMN")
				.verifyComplete();
	}

	@Test
	void zipFlux(){
		Flux<String> charFlux = Flux
				.just("chai", "korsica", "cnmn");
		Flux<String> foodFlux = Flux
				.just("pizza", "coke", "soda");

		Flux<Tuple2<String, String>> zippedFlux =
				Flux.zip(charFlux, foodFlux);

		StepVerifier.create(zippedFlux)
				.expectNextMatches(
						p 	-> 	p.getT1().equals("chai") &&
								p.getT2().equals("pizza")
				)
				.expectNextMatches(
						p 	-> 	p.getT1().equals("korsica") &&
								p.getT2().equals("coke")
				)
				.expectNextMatches(
						p	->	p.getT1().equals("cnmn") &&
								p.getT2().equals("soda")
				)
				.verifyComplete();
	}

	@Test
	void zipFluxToObject(){
		Flux<String> charFlux = Flux
				.just("chai", "korsica", "cnmn");
		Flux<String> foodFlux = Flux
				.just("pizza", "coke", "soda");

		Flux<String> zippedFlux = Flux.zip(charFlux, foodFlux, (c, f) -> c + " likes " + f);

		StepVerifier.create(zippedFlux)
				.expectNext("chai likes pizza")
				.expectNext("korsica likes coke")
				.expectNext("cnmn likes soda")
				.verifyComplete();
	}

	@Test
	public void firstWithSignalFlux(){
		Flux<String> slowFlux = Flux
				.just("chai", "peppermint", "korsica")
				.delaySubscription(Duration.ofMillis(100));
		Flux<String> fastFlux = Flux
				.just("rekka", "zanzo", "memosa");

		Flux<String> firstFlux = Flux.firstWithSignal(slowFlux, fastFlux);

		StepVerifier.create(firstFlux)
				.expectNext("rekka")
				.expectNext("zanzo")
				.expectNext("memosa")
				.verifyComplete();
	}

	@Test
	void skipFlux(){
		Flux<String> skipFlux = Flux
				.just("one", "two", "three", "four", "five")
				.skip(3);

		StepVerifier.create(skipFlux)
				.expectNext("four")
				.expectNext("five")
				.verifyComplete();
	}

	@Test
	void skipFewSeconds(){
		Flux<String> countFlux = Flux
				.just("one", "two" , "skip few", "four", "five")
				.delayElements(Duration.ofSeconds(1))
				.skip(Duration.ofSeconds(4));

		StepVerifier.create(countFlux)
				.expectNext("four", "five")
				.verifyComplete();
	}

	@Test
	void takeFlux(){
		Flux<String> takeFlux = Flux.just(
				"chai", "korsica", "peppermint"
		).take(2);

		StepVerifier.create(takeFlux)
				.expectNext("chai", "korsica")
				.verifyComplete();
	}

	@Test
	void takeFluxBySec(){
		Flux<String> timeFlux = Flux
				.just("one", "two", "three", "four", "five")
				.delayElements(Duration.ofSeconds(1))
				.take(Duration.ofMillis(3500));

		StepVerifier.create(timeFlux)
				.expectNext("one", "two", "three")
				.verifyComplete();
	}

	@Test
	void distinctFlux(){
		Flux<String> disFlux = Flux
				.just("dog", "dog", "cat", "cat", "lizard")
				.distinct();

		StepVerifier.create(disFlux)
				.expectNext("dog", "cat", "lizard")
				.verifyComplete();
	}

	@Test
	void map(){
		Flux<Integer> stringTointFlux = Flux
				.just("hi", "my", "name")
				.map(
						n -> {
							return new Integer(n.length());
						});

		StepVerifier.create(stringTointFlux)
				.expectNext(new Integer(2))
				.expectNext(new Integer(2))
				.expectNext(new Integer(4))
				.verifyComplete();
	}

	@Test
	void flatMap(){
		Flux<Integer> StringToIntegerFlux = Flux
				.just("hi", "this", "is my name")
				.flatMap(n -> Mono.just(n)
						.map(p -> {
									return new Integer(p.length());
						})
						.subscribeOn(Schedulers.parallel())
				);

		List<Integer> integerList = Arrays.asList(
				new Integer(2),
				new Integer(4),
				new Integer(10));

		StepVerifier.create(StringToIntegerFlux)
				.expectNextMatches(p -> integerList.contains(p))
				.expectNextMatches(p -> integerList.contains(p))
				.expectNextMatches(p -> integerList.contains(p))
				.verifyComplete();
	}

	@Test
	void bufferFlux(){
		Flux<String> fruitFlux = Flux.just(
				"apple", "banana", "kiwi", "watermelon", "strawberry"
		);

		Flux<List<String>> bufferFlux = fruitFlux.buffer(3);

		StepVerifier.create(bufferFlux)
				.expectNext(Arrays.asList("apple", "banana", "kiwi"))
				.expectNext(Arrays.asList("watermelon", "strawberry"))
				.verifyComplete();
	}

	@Test
	void bufferAndFlatFlux() throws Exception{
		Flux.just(
				"apple", "banana", "orange", "kiwi", "watermelon")
				.buffer(3)
				.flatMap(x ->
						Flux.fromIterable(x)
								.map(y -> y.toUpperCase())
								.subscribeOn(Schedulers.parallel())
								.log()
				).subscribe();
	}

	@Test
	void collectList(){
		Flux<String> fruitFlux = Flux.just(
				"apple", "banana", "orange", "kiwi", "melon");

		Mono<List<String>> fruitListMono = fruitFlux.collectList();

		StepVerifier
				.create(fruitListMono)
				.expectNext(Arrays.asList(
						"apple", "banana", "orange", "kiwi", "melon"))
				.verifyComplete();
	}

	@Test
	void collectMap(){
		Flux<String> fruitFlux = Flux.just(
				"apple", "banana", "orange", "kiwi", "melon"
		);

		Mono<Map<Integer, String>> fruitMapMono =
				fruitFlux.collectMap(f -> f.length());

		StepVerifier.create(fruitMapMono)
				.expectNextMatches(map ->{
					return
							map.size() == 3 &&
									map.get(5).equals("melon") &&
									map.get(4).equals("kiwi") &&
									map.get(6).equals("orange");
				})
				.verifyComplete();
	}

	@Test
	void allAndany(){
		Flux<String> fruitFlux = Flux.just(
				"apple", "banana", "orange"
		);

		Mono<Boolean> hasAMono = fruitFlux.all(f -> f.contains("a"));
		StepVerifier.create(hasAMono)
				.expectNext(true)
				.verifyComplete();

		Mono<Boolean> hasKMono = fruitFlux.all(f -> f.contains("f"));
		StepVerifier.create(hasKMono)
				.expectNext(false)
				.verifyComplete();

		Mono<Boolean> hasOMono = fruitFlux.any(f -> f.contains("o"));
		StepVerifier.create(hasOMono)
				.expectNext(true)
				.verifyComplete();
	}
}

