
declare module "response" {
    interface ResponseData {
        code: string;
        message: string;
        data: any; // data 필드가 임의의 객체를 포함할 수 있도록 설정
      }      
}