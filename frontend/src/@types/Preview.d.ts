declare module "Preview" {
    interface PreviewProps {
        title: string;
        type: "trace" | "explain" | "blank";
    }
    interface ContentProps {
        type: string;
        
    }
    interface BlankProps {
        text: string;
      }
}