package com.epson.poni.repository.worksheet;

import com.epson.poni.model.worksheet.AnalysisItem;
import com.epson.poni.model.worksheet.Synonyms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SynonymsRepository  extends JpaRepository<Synonyms,Long> {
    List<Synonyms> findByAnalysisItem(AnalysisItem analysisItem);
}
