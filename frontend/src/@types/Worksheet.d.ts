declare module "worksheet" {

    interface Worksheet {
        explanation: Explanation[]|null;
        traceOption: TraceOption|null;
        blank: Blank[]|null;
    }

    interface Analysis {
        word: string;
        grammar: string;
        word_description: string;
        synonyms: string[];
      }
      
      interface Explanation {
        sentence: string;
        speak: string;
        analysis: Analysis[];
      }
      
      interface TraceOption {
        count: number;
        isBlurry: boolean;
        isGrid: boolean;
      }
      
      interface Problem {
        content_kr: string;
        content_en: string;
        answer: string;
      }
      
      interface Blank {
        problems: Problem[];
      }
}