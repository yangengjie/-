package com.ygj;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trie<Integer> trie = new Trie<>();
		trie.add("cat", 1);
		trie.add("dog", 2);
		trie.add("catalog", 3);
		trie.add("cast", 4);
		trie.add("Ğ¡Âë¸ç", 5);
		Asserts.test(trie.size() == 5);
		Asserts.test(trie.startWith("do"));
		Asserts.test(trie.startWith("c"));
		Asserts.test(trie.startWith("ca"));
		Asserts.test(trie.startWith("cat"));
		Asserts.test(trie.startWith("cata"));
		Asserts.test(!trie.startWith("hehe"));
		Asserts.test(trie.get("Ğ¡Âë¸ç") == 5);
		Asserts.test(trie.get("cat") == 1);
		Asserts.test(trie.get("cast") == 4);
		Asserts.test(trie.get("c") == null);
		Asserts.test(trie.remove("cat") == 1);
		Asserts.test(trie.remove("catalog") == 3);
		Asserts.test(trie.remove("cast") == 4);
		Asserts.test(trie.size() == 2);
		Asserts.test(trie.startWith("Ğ¡"));
		Asserts.test(trie.startWith("do"));
		System.out.println(trie.get("cat"));
		System.out.println(trie.get("catalog"));
		System.out.println(trie.get("c"));
		System.out.println(trie.startWith("c"));
		Asserts.test(!trie.startWith("c"));
	}

}
