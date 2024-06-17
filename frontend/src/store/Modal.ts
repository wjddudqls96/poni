// src/state/modalState.ts
import { atom } from 'recoil';

export const modalVisibleState = atom({
  key: 'modalVisibleState',
  default: false,
});

export const modalTypeState = atom({
  key: 'modalTypeState',
  default: "trace",
});
