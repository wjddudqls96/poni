// src/state/modalState.ts
import { BlankOption, TraceOption } from 'Cart';
import { atom } from 'recoil';

export const traceOption = atom<TraceOption>({
  key: 'traceOption',
  default: {
    isBlurry: false,
    isGrid: false,
    count: 1
  },
});

export const blankOption = atom<BlankOption>({
    key: 'blankOption',
    default: {
        count: 0,
        type: "WORD",
        isTranslation: false
    },
});

export const traceSelect = atom<boolean>({
    key: 'traceSelect',
    default: false
});

export const explainSelect = atom<boolean>({
    key: 'explainSelect',
    default: false
});

export const blankSelect = atom<boolean>({
    key: 'blankSelect',
    default: false
});

export const cartIds = atom<any>({
    key: 'cartIds',
    default: null
});

export const pdfUrl = atom<string>({
    key: 'pdfUrl',
    default: ""
});