declare module "Cart" {
    interface Cart {
        traceOption: TraceOption;
        blankOption: BlankOption;
        explanation: boolean;
        content: string;
    }
    interface TraceOption {
        isBlurry: boolean;
        isGrid: boolean;
        count: number;
    }
    interface BlankOption {
        count:number;
        type:"WORD" | "SENTENCE";
        isTranslation: boolean;
    }
}