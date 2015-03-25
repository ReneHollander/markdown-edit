package com.threecrickets.jygments.grammar;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.io.FilenameUtils;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.ObjectMapper;

import com.threecrickets.jygments.Util;

@SuppressWarnings("unchecked")
public class LexerHelper {

	private static final File LEXER_FILE_DIRECTORY = new File("data/highlighting/");
	private static final ConcurrentMap<String, Lexer> LEXERS = new ConcurrentHashMap<String, Lexer>();

	static {

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(Feature.ALLOW_COMMENTS, true);

        System.out.println(LEXER_FILE_DIRECTORY.getAbsolutePath());

		for (File lexerFile : LEXER_FILE_DIRECTORY.listFiles()) {
			try {
				if (FilenameUtils.getExtension(lexerFile.getAbsolutePath()).equals("json")) {
					FileInputStream lexerFileStream = new FileInputStream(lexerFile);

					String lexerJsonInput = Util.rejsonToJson(lexerFileStream);
					Map<String, Object> lexerJson = objectMapper.readValue(lexerJsonInput, HashMap.class);

					Lexer lexer = null;
					String lexerClass = (String) lexerJson.get("class");
					if (lexerClass.equals("regex")) {
						lexer = new RegexLexer();
					} else if (lexerClass.equals("delegated")) {
						lexer = new DelegatedLexer();
					} else {
						lexer = new Lexer();
					}

					lexer.addJson(lexerJson);
					lexer.resolve();
					LEXERS.put(lexerJson.get("name").toString(), lexer);

					List<String> aliasList = ((List<String>) lexerJson.get("aliases"));
					for (String lexerAlias : aliasList) {
						LEXERS.put(lexerAlias, lexer);
					}
				}
			} catch (Exception e) {
				System.err.println("Error while trying to read highlighting template " + lexerFile.getAbsolutePath());
				e.printStackTrace();
			}
		}
	}

	public static Lexer getLexer(String name) {
		return LEXERS.get(name);
	}

}
