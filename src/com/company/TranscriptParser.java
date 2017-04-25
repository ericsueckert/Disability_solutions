package com.company;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.*;
import org.supercsv.prefs.CsvPreference;

import java.io.*;
import java.util.*;

/**
 * Created by Potato on 4/10/17.
 * Object that parses Transcripts in TSV formats.
 */
public class TranscriptParser {
    //Constructs a new TranscriptParser
    public TranscriptParser() {

    }

    //Establish cell columns
    private static CellProcessor[] getProcessors() {

        final CellProcessor[] processors = new CellProcessor[] {
                new NotNull(), //Name tag
                new NotNull() //Text
        };

        return processors;
    }

    //Read tsv file into a list of strings
    public static void readTsvToList(String TSV_FILENAME) throws Exception{
        ICsvListReader listReader = null;
        try {
            listReader = new CsvListReader(new FileReader(TSV_FILENAME), CsvPreference.TAB_PREFERENCE);

            listReader.getHeader(true);

            final CellProcessor[] processors = getProcessors();

            List<Object> transcriptList;

            while( (transcriptList = listReader.read(processors)) != null ) {
                System.out.println(String.format("lineNo=%s, rowNo=%s, transcriptList=%s", listReader.getLineNumber(),
                        listReader.getRowNumber(), transcriptList));
            }
        }
        finally {
            if (listReader != null) {
                listReader.close();
            }
        }
    }

    private static void readTsvToMap(String TSV_FILENAME) throws Exception {

        ICsvMapReader mapReader = null;
        try {
            mapReader = new CsvMapReader(new FileReader(TSV_FILENAME), CsvPreference.TAB_PREFERENCE);

            // the header columns are used as the keys to the Map
            final String[] header = mapReader.getHeader(true);
            final CellProcessor[] processors = getProcessors();

            Map<String, Object> transcriptMap;
            while( (transcriptMap= mapReader.read(header, processors)) != null ) {
                System.out.println(String.format("lineNo=%s, rowNo=%s, transcriptMap=%s", mapReader.getLineNumber(),
                        mapReader.getRowNumber(), transcriptMap));
            }

        }
        finally {
            if( mapReader != null ) {
                mapReader.close();
            }
        }
    }

    private static void writeListToTsv(String TSV_FILENAME) throws Exception {
        ICsvListWriter listWriter = null;
        try {
            listWriter = new CsvListWriter(new FileWriter("temp"), CsvPreference.TAB_PREFERENCE);

        }
    }
}
