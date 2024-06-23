import { BlankOption, TraceOption } from 'Cart';
import { atom } from 'recoil';
import { Worksheet } from 'worksheet';

export const step = atom<number>({
    key: 'step',
    default: 1,
  });

  export const title = atom<string>({
    key: 'title',
    default: "",
  });

  export const type = atom<string>({
    key: 'type',
    default: "step",
  });