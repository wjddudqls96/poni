package com.epson.poni.repository.worksheet;

import com.epson.poni.model.worksheet.AnalysisItem;
import com.epson.poni.model.worksheet.Explanation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnalysisItemRepository  extends JpaRepository<AnalysisItem,Long> {
    List<AnalysisItem> findByExplanation(Explanation explanation);
}
