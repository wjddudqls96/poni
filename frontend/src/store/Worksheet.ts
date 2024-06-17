import { BlankOption, TraceOption } from 'Cart';
import { atom } from 'recoil';
import { Worksheet } from 'worksheet';

export const worksheet = atom<Worksheet>({
    key: 'worksheet',
    default: {
        explanation: null,
        traceOption: null,
        blank: null
    },
  });