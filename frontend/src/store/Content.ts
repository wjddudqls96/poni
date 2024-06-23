import { atom } from "recoil";

export const content = atom<string>({
  key: "content",
  default: "",
});
